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

    data object Register: RegistrationPageStates
}
