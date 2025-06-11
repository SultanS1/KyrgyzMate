package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User

sealed interface LecturerProfileStates : BaseState {

    data class ShowProfileInfo(val user: User) : LecturerProfileStates

    data object Loading : LecturerProfileStates

    data class Error(val message: String) : LecturerProfileStates

    data object Logout : LecturerProfileStates
}