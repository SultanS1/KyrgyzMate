package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.ThemeType
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem

sealed interface ThemesStates : BaseState {

    data object Loading: ThemesStates

    data class ShowThemes(val themes: List<DriveItem>) : ThemesStates

    data class Error(val message: String) : ThemesStates

    data class NavigateToTopics(val themeType: ThemeType) : ThemesStates

    data object Popup : ThemesStates
}