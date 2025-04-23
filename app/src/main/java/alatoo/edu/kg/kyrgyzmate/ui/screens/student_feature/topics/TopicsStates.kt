package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem

sealed interface TopicsStates : BaseState {
    data object Loading: TopicsStates

    data class ShowTopics(val topics: List<DriveItem>) : TopicsStates

    data class Error(val message: String) : TopicsStates

    data object Popup : TopicsStates

    data class NavigateToTopic(val id: String) : TopicsStates
}