package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.domain.user.UserInteractor
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator
import android.util.Log

class RegistrationViewModel(
    private val userInteractor: UserInteractor
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

                    UserRole.UNKOWN -> {}
                }
            }

            RegistrationPageAction.UserComeBack -> checkEmailValidation()
        }
    }

    private fun registerStudent(
        firstName: String,
        lastName: String,
        group: String,
        role: UserRole,
        email: String
    ) {
        val firstNameStatus = ViewModelValidator.validateNameField(firstName)
        val lastNameStatus = ViewModelValidator.validateNameField(lastName)
        val emailStatus = ViewModelValidator.validateEmailField(email)

        if (firstNameStatus != null || lastNameStatus != null || emailStatus != null) {
            _state.value = RegistrationPageStates.RegistrationFieldsState(
                firstNameState = firstNameStatus,
                lastNameState = lastNameStatus,
                emailState = emailStatus
            )
        } else {
            userInteractor.setRegistrationData(
                UserRegistrationData(
                    firstName = firstName,
                    lastName = lastName,
                    group = group,
                    role = role,
                    email = email
                )
            )
            registerUser(email)
            _state.value = RegistrationPageStates.Loading
        }
    }

    private fun registerLecturer(
        firstName: String,
        lastName: String,
        role: UserRole,
        email: String
    ) {
        val firstNameStatus = ViewModelValidator.validateNameField(firstName)
        val lastNameStatus = ViewModelValidator.validateNameField(lastName)
        val emailStatus = ViewModelValidator.validateEmailField(email)

        if (firstNameStatus != null || lastNameStatus != null || emailStatus != null) {
            _state.value = RegistrationPageStates.RegistrationFieldsState(
                firstNameState = firstNameStatus, lastNameState = lastNameStatus, emailState = emailStatus
            )
        } else {
            userInteractor.setRegistrationData(
                UserRegistrationData(
                    firstName = firstName,
                    lastName = lastName,
                    role = role,
                    email = email
                )
            )
            registerUser(email)
            _state.value = RegistrationPageStates.Loading
        }
    }

    private fun checkEmailValidation() {
       launch {
           val result = userInteractor.checkEmailVerificationStatus()
           when (result) {
               FireBasePostResponse.SUCCESS -> _state.value = RegistrationPageStates.EmailVerified
               FireBasePostResponse.EMAIL_NOT_VERIFIED -> {}
               else ->  {
                   _state.value = RegistrationPageStates.Error(R.string.error_unknown_error)
                   Log.e("RegistrationViewModel", "ERROR STATE: ${result.name}")
               }
           }
       }
    }

    private fun registerUser(email: String) = launch {
        val result = userInteractor.registerUser(email)
        when (result) {
            FireBasePostResponse.SUCCESS -> {
                _state.value = RegistrationPageStates.ShowSuccess
            }
            FireBasePostResponse.FAILED -> _state.value =
                RegistrationPageStates.Error(R.string.error_failed_to_register)

            FireBasePostResponse.NETWORK_ERROR -> _state.value =
                RegistrationPageStates.Error(R.string.error_no_internet)

            FireBasePostResponse.INVALID_EMAIL -> _state.value =
                RegistrationPageStates.Error(R.string.error_invalid_email_address)

            FireBasePostResponse.EMAIL_ALREADY_IN_USE -> _state.value =
                RegistrationPageStates.Error(R.string.error_email_already_exists)

            FireBasePostResponse.USER_EXISTS -> _state.value =
                RegistrationPageStates.Error(R.string.error_users_with_current_email_exists)

            else -> {
                _state.value = RegistrationPageStates.Error(R.string.error_unknown_error)
            }
        }
    }

}