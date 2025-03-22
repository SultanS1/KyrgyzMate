package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator

class CreatePasswordViewModel :
    BaseViewModel<CreatePasswordStates, CreatePasswordActions>(initialState = CreatePasswordStates.PasswordFields()) {

    override fun submitAction(action: CreatePasswordActions) {
        when(action) {
            is CreatePasswordActions.CreatePassword -> createPassword(action.password, action.confirmPassword)
        }
    }

    private fun createPassword(password: String, confirmationPassword: String) {
        val passwordState = ViewModelValidator.validatePasswordField(password)
        val confirmPasswordState = ViewModelValidator.arePasswordsSame(password, confirmationPassword)

        if (passwordState != null || confirmPasswordState != null) {
            _state.value = CreatePasswordStates.PasswordFields(
                passwordField = passwordState,
                confirmPasswordField = confirmPasswordState
            )
        } else {
            _state.value = CreatePasswordStates.CreatePassword
        }
    }
}