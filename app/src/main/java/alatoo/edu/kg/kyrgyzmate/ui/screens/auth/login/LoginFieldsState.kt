package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.core.BaseState

sealed interface LoginPageState : BaseState {
    data class LoginFieldsState(
        val emailEmpty: String? = null,
        val passwordEmpty: String? = null,
    ) : LoginPageState
    data object RoleStudent : LoginPageState
    data object RoleLecturer : LoginPageState
    data object ForgotPassword: LoginPageState
    data object Register: LoginPageState
    data object Loading: LoginPageState
    data object OpenGmail: LoginPageState
    data class Error(
        val messageRes: Int
    ): LoginPageState
}
