package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.dialog

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface DialogActions : BaseAction {

    data class LoadData(
        val topicId: String
    ) : DialogActions

    data class AudioPlayed(val position: Int) : DialogActions

    data class PlayAudio(val audioId: String) : DialogActions

    data class StopAudio(val audioId: String) : DialogActions

}