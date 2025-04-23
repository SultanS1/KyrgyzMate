package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface TopicsActions : BaseAction {
    data object Popup : TopicsActions

    data class LoadThemes(val themeId: String) : TopicsActions

    data class OpenTopic(val topicId: String) : TopicsActions
}