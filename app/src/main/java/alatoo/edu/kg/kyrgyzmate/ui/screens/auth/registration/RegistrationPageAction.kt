package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.core.BaseAction
import alatoo.edu.kg.kyrgyzmate.domain.model.role.UserRole

sealed interface RegistrationPageAction : BaseAction{
    data class SubmitUserData(
        val firstName: String,
        val lastName: String,
        val group: String,
        val role: UserRole,
        val email: String
    ): RegistrationPageAction

    data object Popup: RegistrationPageAction
}