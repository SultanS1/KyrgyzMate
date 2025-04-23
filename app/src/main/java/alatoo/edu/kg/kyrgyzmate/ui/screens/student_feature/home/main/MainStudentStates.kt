package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.ParentInfo
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo

sealed interface MainStudentStates : BaseState {

    data object Loading : MainStudentStates

    data class ShowLevels(val levels: List<LevelInfo>) : MainStudentStates

    data class EnterCourse(val folderInfo: ParentInfo) : MainStudentStates

    data class Error(val message: String) : MainStudentStates
}