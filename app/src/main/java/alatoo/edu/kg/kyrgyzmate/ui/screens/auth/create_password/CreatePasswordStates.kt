package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.core.BaseState

sealed interface CreatePasswordStates : BaseState {
    data object PasswordCreateSuccess: CreatePasswordStates
    data object PasswordCreateFailed: CreatePasswordStates
    data class PasswordFields(
        val passwordField: String? = null,
        val confirmPasswordField: String? = null
    ) : CreatePasswordStates
    data object Loading: CreatePasswordStates
}
