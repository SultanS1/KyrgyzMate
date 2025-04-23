package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes

import alatoo.edu.kg.kyrgyzmate.core.BaseAction
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.ThemeType

sealed interface ThemesActions : BaseAction {

    data object Popup : ThemesActions

    data class OpenTopics(val themeType: ThemeType) : ThemesActions

    data class LoadThemes(val levelId: String) : ThemesActions
}