package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.domain.user.UserInteractor
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator

class LoginViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel<LoginPageState, LoginPageActions>(initialState = LoginPageState.LoginFieldsState()) {

    override fun submitAction(action: LoginPageActions) {
        when(action) {
            is LoginPageActions.ForgotPassword -> forgotPassword(action.email)
            is LoginPageActions.Register -> _state.value = LoginPageState.Register
            is LoginPageActions.SubmitData -> login(action.email, action.password)
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
            _state.value = LoginPageState.Loading
            loginRequest(loginField, passwordField)
        }
    }

    private fun forgotPassword(email: String) {
        val loginError = ViewModelValidator.validateEmailField(email)

        if (loginError != null) {
            _state.value = LoginPageState.LoginFieldsState(
                emailEmpty = loginError
            )
        } else {
            _state.value = LoginPageState.Loading
            forgotPasswordRequest(email)
        }
    }

    private fun forgotPasswordRequest(email: String) {
        launch {
            val result = userInteractor.resetPassword(email)
            when (result) {
                FireBasePostResponse.SUCCESS -> {
                    _state.value = LoginPageState.OpenGmail
                }
                FireBasePostResponse.INVALID_EMAIL -> {
                    _state.value = LoginPageState.Error(R.string.error_invalid_email_address)
                }
                else -> {
                    _state.value = LoginPageState.Error(R.string.error_unknown_error)
                }
            }
        }
    }

    private fun loginRequest(email: String, password: String) {
        launch {
            val result = userInteractor.loginUser(email, password)
            when (result) {
                FireBasePostResponse.SUCCESS -> {
                    val role = userInteractor.getUserRole()
                    when(role) {
                        UserRole.STUDENT -> _state.value = LoginPageState.RoleStudent

                        UserRole.LECTURER -> _state.value = LoginPageState.RoleLecturer

                        UserRole.UNKOWN -> _state.value = LoginPageState.Error(R.string.error_unknown_error)
                    }
                }
                FireBasePostResponse.WRONG_CREDENTIALS -> {
                    _state.value = LoginPageState.Error(R.string.error_wrong_credentials)
                }
                else -> {
                    _state.value = LoginPageState.Error(R.string.error_unknown_error)
                }
            }
        }
    }
}