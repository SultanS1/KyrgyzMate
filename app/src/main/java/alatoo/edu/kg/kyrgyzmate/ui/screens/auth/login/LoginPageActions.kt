package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface LoginPageActions : BaseAction {
    data class SubmitData(
        val email: String,
        val password: String
    ): LoginPageActions

    data class ForgotPassword(val email: String): LoginPageActions

    data object Register: LoginPageActions
}