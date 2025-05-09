package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.edit_profile

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.domain.student.StudentInteractor
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration.RegistrationPageStates
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator

class EditProfileViewModel(
    private val studentInteractor: StudentInteractor
) : BaseViewModel<EditProfileStates, EditProfileActions>(initialState = EditProfileStates.ProfileFields()) {

    override fun submitAction(action: EditProfileActions) {
        when (action) {
            is EditProfileActions.Cancel -> _state.value = EditProfileStates.NavigateBack
            is EditProfileActions.LoadData -> loadData()
            is EditProfileActions.SubmitData -> submitData(action.firstName, action.lastName, action.group)
        }
    }

    private fun loadData() {
        launch {
            val groups = studentInteractor.getGroupList()
            if(groups is FirebaseGetResponse.Success) {
                _state.value = EditProfileStates.ShowProfileData(
                    groups.data
                )
            }
        }
    }

    private fun submitData(
        firstName: String,
        lastName: String,
        group: GroupItem?,
    ) {
        val firstNameStatus = ViewModelValidator.validateNameField(firstName)
        val lastNameStatus = ViewModelValidator.validateNameField(lastName)

        if (firstNameStatus != null || lastNameStatus != null) {
            _state.value = EditProfileStates.ProfileFields(
                firstNameState = firstNameStatus,
                lastNameState = lastNameStatus,
            )
        } else {
            _state.value = EditProfileStates.Loading
            launch {
                val result = studentInteractor.updateProfile(firstName, lastName, group)
                when(result) {
                    FireBasePostResponse.SUCCESS -> {
                        studentInteractor.getUserProfile(true)
                        _state.value = EditProfileStates.NavigateBack
                    }
                    else -> _state.value = EditProfileStates.Error(R.string.error_unknown_error)
                }
            }
        }
    }
}