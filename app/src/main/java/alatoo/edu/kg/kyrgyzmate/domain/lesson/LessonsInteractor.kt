package alatoo.edu.kg.kyrgyzmate.domain.lesson

import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Dialog
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Word
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.toDialog
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.toWord
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsRestRepository
import alatoo.edu.kg.kyrgyzmate.services.DialogParser
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

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
                .filter { it.isFolder }
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
}