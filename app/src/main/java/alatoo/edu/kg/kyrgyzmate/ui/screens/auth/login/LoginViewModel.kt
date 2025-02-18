package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : BaseViewModel() {

    val fieldsState: StateFlow<LoginPageState>
        get() = _fieldsState
    private val _fieldsState: MutableStateFlow<LoginPageState> = MutableStateFlow(LoginPageState.LoginFieldsState())

    fun loginAction(loginField: String, passwordField: String) {
        val loginError = ViewModelValidator.validateLoginField(loginField)
        val passwordError = ViewModelValidator.validatePasswordField(passwordField)

        if (loginError != null || passwordError != null) {
            _fieldsState.value = LoginPageState.LoginFieldsState(
                emailEmpty = loginError,
                passwordEmpty = passwordError
            )
        } else {
            _fieldsState.value = LoginPageState.RoleStudent
        }
    }
}