package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface CreatePasswordActions : BaseAction {
    data class CreatePassword(
        val password: String,
        val confirmPassword: String
    ) : CreatePasswordActions
}