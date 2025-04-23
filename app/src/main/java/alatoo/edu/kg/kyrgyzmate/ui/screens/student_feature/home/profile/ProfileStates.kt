package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile

sealed interface ProfileStates : BaseState {

    data object Loading : ProfileStates

    data class ShowProfile(val profile: StudentProfile) : ProfileStates

    data class Error(val message: String) : ProfileStates
}