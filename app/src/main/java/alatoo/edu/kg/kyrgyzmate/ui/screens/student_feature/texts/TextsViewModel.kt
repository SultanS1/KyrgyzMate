package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.texts

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor

class TextsViewModel(
    private val lessonsInteractor: LessonsInteractor
): BaseViewModel<TextStates, TextsActions>(initialState = TextStates.Loading) {

    override fun submitAction(action: TextsActions) {
        when(action) {
            is TextsActions.LoadData -> loadData(id = action.id)
        }
    }

    private fun loadData(id: String) {
        launch {
            val text = lessonsInteractor.getText(id)
            _state.value = TextStates.ShowText(text.text, text.audio)
        }
    }
}