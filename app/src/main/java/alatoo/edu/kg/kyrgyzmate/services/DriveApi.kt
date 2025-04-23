package alatoo.edu.kg.kyrgyzmate.services

import alatoo.edu.kg.kyrgyzmate.core.BaseUrls
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object DriveApi {
    private const val BASE_URL = "https://www.googleapis.com/drive/v3/files"
    private const val TAG = "DriveApi"

    fun listChildren(parentId: String): List<DriveItem> {
        val query = "'$parentId' in parents and trashed = false"
        val encodedQuery = URLEncoder.encode(query, "UTF-8")
        val url = "$BASE_URL?q=$encodedQuery&fields=files(id,name,mimeType)&key=${BaseUrls.API_KEY}"

        Log.d(TAG, "Request URL: $url")

        return try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            val response = conn.inputStream.bufferedReader().use { it.readText() }
            Log.d(TAG, "Response: $response")

            val files = JSONObject(response).optJSONArray("files") ?: return emptyList()

            (0 until files.length()).map { i ->
                val file = files.getJSONObject(i)
                DriveItem(
                    id = file.getString("id"),
                    name = file.getString("name"),
                    isFolder = file.getString("mimeType") == "application/vnd.google-apps.folder"
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error listing children: ${e.message}", e)
            emptyList()
        }
    }
}