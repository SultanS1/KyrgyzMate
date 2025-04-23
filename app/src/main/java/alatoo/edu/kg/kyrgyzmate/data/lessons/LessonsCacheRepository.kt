package alatoo.edu.kg.kyrgyzmate.data.lessons

import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Dialog
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Word
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo
import kotlinx.coroutines.flow.MutableStateFlow

class LessonsCacheRepository : LessonsLocalRepository {

    private val levelsFlow: MutableStateFlow<List<LevelInfo>?> = MutableStateFlow(null)

    private val themesFlow: MutableStateFlow<MutableMap<String, List<DriveItem>?>> = MutableStateFlow(mutableMapOf())

    private val topicsFlow: MutableStateFlow<MutableMap<String, List<DriveItem>?>> = MutableStateFlow(mutableMapOf())

    private val dialogsFlow: MutableStateFlow<MutableMap<String, List<Dialog>?>> = MutableStateFlow(mutableMapOf())

    private val wordsFlow: MutableStateFlow<MutableMap<String, List<Word>?>> = MutableStateFlow(mutableMapOf())

    override fun setLevels(levels: List<LevelInfo>) {
        levelsFlow.value = levels
    }

    override fun getLevels(): List<LevelInfo>? {
        return levelsFlow.value
    }

    override fun setThemes(levelId: String, themes: List<DriveItem>) {
        val currentThemes = themesFlow.value.toMutableMap()
        currentThemes[levelId] = themes
        themesFlow.value = currentThemes
    }

    override fun getThemes(levelId: String): List<DriveItem>? {
        return themesFlow.value[levelId]
    }

    override fun getTopics(themeId: String): List<DriveItem>? {
        return topicsFlow.value[themeId]
    }

    override fun setTopics(themeId: String, dialogs: List<DriveItem>) {
        val currentTopics = topicsFlow.value.toMutableMap()
        currentTopics[themeId] = dialogs
        topicsFlow.value = currentTopics
    }

    override fun setDialogs(topicId: String, dialogs: List<Dialog>) {
        val currentDialogs = dialogsFlow.value.toMutableMap()
        currentDialogs[topicId] = dialogs
        dialogsFlow.value = currentDialogs
    }

    override fun getDialogs(topicId: String): List<Dialog>? {
        return dialogsFlow.value[topicId]
    }

    override fun setWords(topicId: String, words: List<Word>) {
        val currentWords = wordsFlow.value.toMutableMap()
        currentWords[topicId] = words
        wordsFlow.value = currentWords
    }

    override fun getWords(topicId: String): List<Word>? {
        return wordsFlow.value[topicId]
    }
}