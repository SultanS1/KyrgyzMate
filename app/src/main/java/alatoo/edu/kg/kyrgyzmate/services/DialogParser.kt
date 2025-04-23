package alatoo.edu.kg.kyrgyzmate.services

import alatoo.edu.kg.kyrgyzmate.core.BaseUrls
import alatoo.edu.kg.kyrgyzmate.services.models.AudioItem
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object DialogParser {

    suspend fun parseFromDriveFolder(context: Context, folderId: String): List<AudioItem> {
        val files = DriveApi.listChildren(folderId)

        val textFile = files.find { it.name.equals("text", ignoreCase = true) }
            ?: throw IllegalArgumentException("Text file not found in folder")

        val textContent = DocsReader.readTextFile(textFile.id)

        val audioMap = files
            .filter { it.name.endsWith(".mp3") }
            .mapNotNull {
                val number = it.name.removeSuffix(".mp3").toIntOrNull()
                if (number != null) number to it.id else null
            }
            .toMap()

        val blocks = textContent.split(Regex("""(?m)^\d+\."""))
        val dialogs = mutableListOf<AudioItem>()

        for ((index, block) in blocks.drop(0).withIndex()) {
            val audioNumber = index + 1

            val kyLine = Regex("""Kyrgyz:\s*(.+)""").find(block)?.groupValues?.get(1)?.trim()
            val enLine = Regex("""English:\s*(.+)""").find(block)?.groupValues?.get(1)?.trim()

            if (kyLine != null && enLine != null) {
                val audioFileId = audioMap[audioNumber] ?: "missing"

                val audioFile = if (audioFileId != null) {
                    try {
                        cacheAudioFromDrive(context, audioFileId)
                    } catch (e: Exception) {
                        Log.e("DialogParser", "Error caching audio file: $audioFileId", e)
                        File("")
                    }
                } else {
                    Log.e("DialogParser", "Error getting audio file: $audioFileId")
                    File("")
                }

                dialogs.add(
                    AudioItem(
                        audioNumber = audioNumber,
                        ky = kyLine,
                        en = enLine,
                        audioFile = audioFile
                    )
                )
            }
        }

        return dialogs
    }

    private suspend fun cacheAudioFromDrive(context: Context, fileId: String): File = withContext(Dispatchers.IO) {
        val cacheFile = File(context.cacheDir, "$fileId.mp3")

        if (cacheFile.exists() && cacheFile.length() > 0) return@withContext cacheFile

        val url = "https://www.googleapis.com/drive/v3/files/$fileId?alt=media&key=${BaseUrls.API_KEY}"

        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connect()

            connection.inputStream.use { input ->
                FileOutputStream(cacheFile).use { output ->
                    input.copyTo(output)
                }
            }
            if (cacheFile.length() == 0L) {
                cacheFile.delete()
                throw IOException("Downloaded file is empty: $fileId")
            }
        } catch (e: Exception) {
            throw IOException("Failed to download audio file: $fileId", e)
        }

        return@withContext cacheFile
    }
}
