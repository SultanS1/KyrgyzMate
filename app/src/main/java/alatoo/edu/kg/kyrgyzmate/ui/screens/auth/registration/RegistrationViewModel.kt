package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.domain.model.role.UserRole
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator

class RegistrationViewModel(

) : BaseViewModel<RegistrationPageStates, RegistrationPageAction>(initialState = RegistrationPageStates.RegistrationFieldsState()) {

    override fun submitAction(action: RegistrationPageAction) {
        when (action) {
            is RegistrationPageAction.Popup -> _state.value = RegistrationPageStates.Popup

            is RegistrationPageAction.SubmitUserData -> {
                when (action.role) {
                    UserRole.STUDENT -> registerStudent(
                        action.firstName, action.lastName, action.group, action.role, action.email
                    )

                    UserRole.LECTURER -> registerLecturer(
                        action.firstName, action.lastName, action.role, action.email
                    )
                }
            }
        }
    }

    private fun registerStudent(
        firstNameField: String,
        lastNameField: String,
        group: String,
        role: UserRole,
        emailField: String
    ) {
        val firstName = ViewModelValidator.validateNameField(firstNameField)
        val lastName = ViewModelValidator.validateNameField(lastNameField)
        val group = ViewModelValidator.validateSelectorField(group)
        val email = ViewModelValidator.validateEmailField(emailField)

        if (firstName != null || lastName != null || email != null) {
            _state.value = RegistrationPageStates.RegistrationFieldsState(
                firstNameState = firstName,
                lastNameState = lastName,
                groupState = group,
                emailState = email
            )
        } else {
            //TODO Should send data to back
            _state.value = RegistrationPageStates.Register
        }
    }

    private fun registerLecturer(
        firstNameField: String,
        lastNameField: String,
        role: UserRole,
        emailField: String
    ) {
        val firstName = ViewModelValidator.validateNameField(firstNameField)
        val lastName = ViewModelValidator.validateNameField(lastNameField)
        val email = ViewModelValidator.validateEmailField(emailField)

        if (firstName != null || lastName != null || email != null) {
            _state.value = RegistrationPageStates.RegistrationFieldsState(
                firstNameState = firstName, lastNameState = lastName, emailState = email
            )
        } else {
            //TODO Should send data to back
            _state.value = RegistrationPageStates.Register
        }
    }
}