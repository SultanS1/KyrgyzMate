package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface LoginPageActions : BaseAction {
    data class SubmitData(
        val email: String,
        val password: String
    ): LoginPageActions

    data object ForgotPassword: LoginPageActions

    data object Register: LoginPageActions
}