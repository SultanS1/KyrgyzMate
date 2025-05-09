package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.core.BaseAction
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole

sealed interface RegistrationPageAction : BaseAction{
    data class SubmitUserData(
        val firstName: String,
        val lastName: String,
        val role: UserRole,
        val email: String
    ): RegistrationPageAction

    data object Popup: RegistrationPageAction

    data object UserComeBack : RegistrationPageAction
}