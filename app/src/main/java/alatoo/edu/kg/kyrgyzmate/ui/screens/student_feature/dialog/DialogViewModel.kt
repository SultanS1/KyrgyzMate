package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.dialog

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor

class DialogViewModel(
    private val lessonsInteractor: LessonsInteractor
) : BaseViewModel<DialogStates, DialogActions>(initialState = DialogStates.Loading) {

    override fun submitAction(action: DialogActions) {
        when(action) {
            is DialogActions.LoadData -> loadDialogs(action.topicId)
            is DialogActions.PlayAudio -> {}
            is DialogActions.StopAudio -> {}
            is DialogActions.AudioPlayed -> _state.value = DialogStates.UpdatePlayedData(action.position)
        }
    }

    private fun loadDialogs(topicId: String) {
        launch {
            try {
                _state.value = DialogStates.Loading
                val result = lessonsInteractor.getDialogs(topicId)
                _state.value = DialogStates.ShowData(result)
            } catch (t: Throwable) {
                _state.value = DialogStates.Error(t.message.toString())
            }
        }
    }
}