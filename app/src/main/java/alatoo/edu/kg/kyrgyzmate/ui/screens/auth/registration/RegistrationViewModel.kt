package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationViewModel : BaseViewModel() {

    val fieldsState: StateFlow<RegistrationFieldsState>
        get() = _fieldsState
    private val _fieldsState: MutableStateFlow<RegistrationFieldsState> = MutableStateFlow(RegistrationFieldsState())

    fun registerAction(
        firstNameField: String,
        lastNameField: String,
        emailField: String
    ) {
        val firstName = ViewModelValidator.validateNameField(firstNameField)
        val lastName = ViewModelValidator.validateNameField(lastNameField)
        val login = ViewModelValidator.validateLoginField(emailField)

        if (firstName != null || lastName  != null || login != null) {
            _fieldsState.value = RegistrationFieldsState(
                firstNameState = firstName,
                lastNameState = lastName,
                emailState = login
            )
        } else {
        }
    }
}