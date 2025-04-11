package alatoo.edu.kg.kyrgyzmate.services

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL

object DocsReader {
    suspend fun readTextFile(fileId: String): String = withContext(Dispatchers.IO) {
        try {
            val url = URL("https://docs.google.com/document/d/$fileId/export?format=txt")
            val connection = url.openConnection() as HttpURLConnection

            connection.instanceFollowRedirects = true
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")

            val responseCode = connection.responseCode

            if (responseCode in 300..399) {
                val redirectUrl = connection.getHeaderField("Location")
                val redirectedConnection = URL(redirectUrl).openConnection() as HttpURLConnection
                redirectedConnection.setRequestProperty("User-Agent", "Mozilla/5.0")
                return@withContext redirectedConnection.inputStream.bufferedReader().use { it.readText() }
            }

            connection.inputStream.bufferedReader().use { it.readText() }
        } catch (e: FileNotFoundException) {
            Log.e("DocsReader", "File not found or not accessible: $fileId", e)
            "File not found or not accessible"
        } catch (e: Exception) {
            Log.e("DocsReader", "Error reading file: $fileId", e)
            "Error reading file"
        }
    }
}

