package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.dialog

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Dialog

sealed interface DialogStates : BaseState {

    data object Loading : DialogStates

    data class ShowData(val dialogs: List<Dialog>) : DialogStates

    data class UpdatePlayedData(val position: Int) : DialogStates

    data class Error(val message: String) : DialogStates
}