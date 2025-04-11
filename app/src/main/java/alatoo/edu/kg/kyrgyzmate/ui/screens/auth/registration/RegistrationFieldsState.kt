package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.core.BaseState


sealed interface RegistrationPageStates : BaseState{
    data class RegistrationFieldsState(
        val firstNameState: String? = null,
        val lastNameState: String? = null,
        val groupState: String? = null,
        val emailState: String? = null,
    ): RegistrationPageStates

    data object Popup: RegistrationPageStates

    data class Error(val messageRes: Int): RegistrationPageStates

    data object Loading: RegistrationPageStates

    data object ShowSuccess: RegistrationPageStates

    data object EmailVerified: RegistrationPageStates
}
