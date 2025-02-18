package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

sealed interface LoginPageState {
    data class LoginFieldsState(
        val emailEmpty: String? = null,
        val passwordEmpty: String? = null,
    ) : LoginPageState
    data object RoleStudent : LoginPageState
    data object RoleLecturer : LoginPageState
}
