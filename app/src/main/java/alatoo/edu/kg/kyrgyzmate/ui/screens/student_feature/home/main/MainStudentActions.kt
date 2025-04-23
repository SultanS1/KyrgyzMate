package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main

import alatoo.edu.kg.kyrgyzmate.core.BaseAction
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo

sealed interface MainStudentActions : BaseAction {

    data class OpenCourses(
        val levelInfo: LevelInfo
    ) : MainStudentActions

    data object LoadData : MainStudentActions
}