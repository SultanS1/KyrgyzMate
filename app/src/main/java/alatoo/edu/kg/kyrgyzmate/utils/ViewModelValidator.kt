package alatoo.edu.kg.kyrgyzmate.utils

object ViewModelValidator {

    fun validateLoginField(loginField: String): String? {
        return when {
            loginField.isEmpty() -> "Login cannot be empty"
            !loginField.contains('@') -> "Wrong email address"
            else -> null
        }
    }

    fun validatePasswordField(passwordField: String): String? {
        return when {
            passwordField.isEmpty() -> "Password cannot be empty"
            passwordField.length < 5 -> "Password should be at least 5 characters"
            else -> null
        }
    }

    fun validateNameField(nameField: String): String? {
        return when {
            nameField.isEmpty() -> "Name cannot be empty"
            nameField.length < 3 -> "Name should be at least 3 characters"
            else -> null
        }
    }
}