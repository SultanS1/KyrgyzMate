package alatoo.edu.kg.kyrgyzmate.data.lessons

import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Dialog
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Word
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo

interface LessonsLocalRepository {

    fun setLevels(levels: List<LevelInfo>)

    fun getLevels(): List<LevelInfo>?

    fun setThemes(levelId: String, themes: List<DriveItem>)

    fun getThemes(levelId: String): List<DriveItem>?

    fun getTopics(themeId: String): List<DriveItem>?

    fun setTopics(themeId: String, dialogs: List<DriveItem>)

    fun setDialogs(topicId: String, dialogs: List<Dialog>)

    fun getDialogs(topicId: String): List<Dialog>?

    fun setWords(topicId: String, words: List<Word>)

    fun getWords(topicId: String): List<Word>?

}