package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor

class TopicsViewModel(
    private val lessonsInteractor: LessonsInteractor
) :
    BaseViewModel<TopicsStates, TopicsActions>(initialState = TopicsStates.Loading) {
    override fun submitAction(action: TopicsActions) {
        when(action) {
            is TopicsActions.LoadThemes -> loadTopics(action.themeId)
            is TopicsActions.Popup -> _state.value = TopicsStates.Popup
            is TopicsActions.OpenTopic -> _state.value = TopicsStates.NavigateToTopic(action.topicId)
        }
    }

    private fun loadTopics(themeId: String) {
        launch {
            try {
                _state.value = TopicsStates.Loading
                val result = lessonsInteractor.getTopics(themeId)
                _state.value = TopicsStates.ShowTopics(result)
            } catch (t: Throwable) {
                _state.value = TopicsStates.Error(t.message.toString())
            }
        }
    }
}