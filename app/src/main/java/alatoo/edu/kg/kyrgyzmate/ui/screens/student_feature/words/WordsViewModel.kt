package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.words

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor

class WordsViewModel(
    private val lessonsInteractor: LessonsInteractor
) : BaseViewModel<WordsStates, WordsActions>(initialState = WordsStates.Loading) {
    override fun submitAction(action: WordsActions) {
        when(action) {
            is WordsActions.LoadWords -> loadWords(action.topicId)
            is WordsActions.AudioPlayed -> _state.value = WordsStates.UpdatePlayedAudio(action.position)
        }
    }

    private fun loadWords(topicId: String) {
        launch {
            try {
                _state.value = WordsStates.Loading
                val result = lessonsInteractor.getWords(topicId)
                _state.value = WordsStates.ShowWords(result)
            } catch (t: Throwable) {
                _state.value = WordsStates.Error(t.message.toString())
            }
        }
    }
}