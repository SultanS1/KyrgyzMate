package alatoo.edu.kg.kyrgyzmate.domain.lesson

import alatoo.edu.kg.kyrgyzmate.core.BaseUrls
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Dialog
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Text
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Word
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.toDialog
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.toWord
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsRestRepository
import alatoo.edu.kg.kyrgyzmate.services.DialogParser
import alatoo.edu.kg.kyrgyzmate.services.DocsReader
import alatoo.edu.kg.kyrgyzmate.services.DriveApi
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class LessonsInteractor(
    private val localRepository: LessonsLocalRepository,
    private val restRepository: LessonsRestRepository,
    private val context: Context
) {

    suspend fun getLevels(refresh: Boolean = false): List<LevelInfo> {
        val levels = localRepository.getLevels()
        if(refresh || levels == null) {
            val fetchedLevels = fetchLevels()
            localRepository.setLevels(fetchedLevels)
            return fetchedLevels
        }
        return levels
    }

    private suspend fun fetchLevels(): List<LevelInfo> = withContext(Dispatchers.IO) {
        val levelFolders = restRepository.getLevels()

        levelFolders.map { level ->
            async {
                val children = restRepository.getItems(level.id)

                val descriptionFile = children.firstOrNull {
                    it.name.contains("description", ignoreCase = true) && !it.isFolder
                }

                val contentFolder = children.firstOrNull {
                    it.isFolder && it.name.equals("content", ignoreCase = true)
                }

                val descriptionText = descriptionFile?.id?.let {
                    restRepository.getDocsText(it)
                } ?: "Description not found"

                LevelInfo(
                    name = level.name.replace(Regex("\\d"), ""),
                    folderId = contentFolder?.id ?: "",
                    description = descriptionText,
                    lessons = "5 lessons",
                    progress = 1
                )
            }
        }.awaitAll()
    }

    suspend fun getThemes(levelId: String, refresh: Boolean = false): List<DriveItem> = withContext(Dispatchers.IO) {
        val themes = localRepository.getThemes(levelId)
        if(refresh || themes == null) {
            val fetchedThemes = restRepository.getItems(levelId)
                .filter { it.isFolder }
                .sortedBy {
                    Regex("""^\d+""").find(it.name)?.value?.toIntOrNull() ?: Int.MAX_VALUE
                }.map { it.copy(name = it.name.replace(Regex("\\d"), "")) }
            localRepository.setThemes(levelId, fetchedThemes)
            return@withContext fetchedThemes
        }
        themes
    }

    suspend fun getTopics(themeId: String, refresh: Boolean = false): List<DriveItem> = withContext(Dispatchers.IO) {
        val topics = localRepository.getTopics(themeId)
        if(refresh || topics == null) {
            val fetchedTopics = restRepository.getItems(themeId)
                .sortedBy {
                    Regex("""^\d+""").find(it.name)?.value?.toIntOrNull() ?: Int.MAX_VALUE
                }.map { it.copy(name = it.name.replace(Regex("\\d"), "")) }
            localRepository.setTopics(themeId, fetchedTopics)
            return@withContext fetchedTopics
        }
        topics
    }

    suspend fun getDialogs(topicId: String, refresh: Boolean = false): List<Dialog> = withContext(Dispatchers.IO) {
        val dialogs = localRepository.getDialogs(topicId)
        if(refresh || dialogs == null) {
            val fetchedDialogs = DialogParser.parseFromDriveFolder(context, topicId).map { it.toDialog() }
            localRepository.setDialogs(topicId, fetchedDialogs)
            return@withContext fetchedDialogs
        }
        dialogs
    }

    suspend fun getWords(topicId: String, refresh: Boolean = false): List<Word> = withContext(Dispatchers.IO) {
        val words = localRepository.getWords(topicId)
        if(refresh || words == null) {
            val fetchedWords = DialogParser.parseFromDriveFolder(context, topicId).map { it.toWord() }
            localRepository.setWords(topicId, fetchedWords)
            return@withContext fetchedWords
        }
        words
    }

    suspend fun getText(topicId: String, refresh: Boolean = false): Text = withContext(Dispatchers.IO) {
        val text = localRepository.getText(topicId)
        if (refresh || text == null) {
            val files = DriveApi.listChildren(topicId)

            val textFile = files.find {
                it.name.endsWith(".txt", ignoreCase = true) || it.name.equals("text", ignoreCase = true)
            } ?: throw IllegalArgumentException("Text file not found in folder: $topicId")

            val audioFile = files.find {
                it.name.endsWith(".mp3", ignoreCase = true)
            } ?: throw IllegalArgumentException("Audio file not found in folder: $topicId")

            val text = DocsReader.readTextFile(textFile.id)

            val audio = cacheAudioFromDrive(context, audioFile.id)

            val fetchedText = Text(text = text, audio = audio)
            localRepository.setText(topicId, fetchedText)
            Log.d("LessonsInteractor", "Fetched text: $fetchedText")
            return@withContext fetchedText
        }
        text
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