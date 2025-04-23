package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.words

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface WordsActions : BaseAction {

    data class LoadWords(val topicId: String): WordsActions

    data class AudioPlayed(val position: Int): WordsActions
}