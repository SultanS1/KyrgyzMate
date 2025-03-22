package alatoo.edu.kg.kyrgyzmate.utils

import android.util.Patterns

object ViewModelValidator {

    fun validateEmailField(email: String): String? {
        return when {
            email.isEmpty() -> "Email cannot be empty"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Wrong email address"
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

    fun arePasswordsSame(passwordField: String, confirmPasswordField: String): String? {
        return when {
            passwordField.equals(confirmPasswordField) -> null
            else -> "Wrong confirmation password"
        }
    }

    fun validateSelectorField(selectedItem: String): String? {
        return when {
            selectedItem.isEmpty() -> "Please select an option"
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