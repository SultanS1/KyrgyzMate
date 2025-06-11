package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface LecturerProfileActions : BaseAction {

    data object Load: LecturerProfileActions

    data object Logout: LecturerProfileActions
}