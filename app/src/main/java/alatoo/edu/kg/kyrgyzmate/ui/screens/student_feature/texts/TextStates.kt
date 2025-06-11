package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.texts

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import java.io.File

sealed interface TextStates : BaseState {

    data object Loading : TextStates

    data class Error(val messageRes: Int) : TextStates

    data class ShowText(val text: String, val audio: File) : TextStates
}