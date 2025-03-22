package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator

class LoginViewModel(

) : BaseViewModel<LoginPageState, LoginPageActions>(initialState = LoginPageState.LoginFieldsState()) {

    override fun submitAction(action: LoginPageActions) {
        when(action) {
            is LoginPageActions.ForgotPassword -> _state.value = LoginPageState.ForgotPassword
            is LoginPageActions.Register -> _state.value = LoginPageState.Register
            is LoginPageActions.SubmitData -> login(action.email, action.password)
            else -> {}
        }
    }

    private fun login(loginField: String, passwordField: String) {
        val loginError = ViewModelValidator.validateEmailField(loginField)
        val passwordError = ViewModelValidator.validatePasswordField(passwordField)

        if (loginError != null || passwordError != null) {
            _state.value = LoginPageState.LoginFieldsState(
                emailEmpty = loginError,
                passwordEmpty = passwordError
            )
        } else {
            _state.value = LoginPageState.RoleStudent
        }
    }
}