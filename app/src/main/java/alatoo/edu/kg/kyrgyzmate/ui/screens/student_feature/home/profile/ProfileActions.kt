package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface ProfileActions : BaseAction {

    data class LoadProfile(val refresh: Boolean = false) : ProfileActions

    data object ShowEditProfile : ProfileActions

    data object Logout : ProfileActions
}