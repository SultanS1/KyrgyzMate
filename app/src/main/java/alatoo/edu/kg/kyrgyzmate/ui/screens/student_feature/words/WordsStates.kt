package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.words

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Word

sealed interface WordsStates : BaseState {

    data object Loading : WordsStates

    data class ShowWords(val words: List<Word>) : WordsStates

    data class UpdatePlayedAudio(val position: Int) : WordsStates

    data class Error(val message: String) : WordsStates
}