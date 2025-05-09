package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.new_group

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.domain.lecturer.LecturerInteractor
import alatoo.edu.kg.kyrgyzmate.utils.ViewModelValidator

class CreateNewGroupViewModel(
    private val lecturerInteractor: LecturerInteractor
) : BaseViewModel<CreateNewGroupStates, CreateNewGroupActions>(initialState = CreateNewGroupStates.GroupFieldsState()) {

    override fun submitAction(action: CreateNewGroupActions) {
        when(action) {
            is CreateNewGroupActions.SubmitData -> validateData(action.name, action.description)
            is CreateNewGroupActions.Popup -> _state.value = CreateNewGroupStates.Popup
        }
    }

    private fun validateData(name: String, description: String) {

        val nameState = ViewModelValidator.validateNameField(name)
        val descriptionState = ViewModelValidator.validateNameField(description)

        if(nameState == null && descriptionState == null) {
            submitData(name, description)
        }
        _state.value = CreateNewGroupStates.GroupFieldsState(nameState, descriptionState)
    }

    private fun submitData(name: String, description: String) {
        launch {
            _state.value = CreateNewGroupStates.Loading
            val result = lecturerInteractor.createGroup(name, description)
            when (result) {
                FireBasePostResponse.SUCCESS -> _state.value = CreateNewGroupStates.Success
                else -> _state.value = CreateNewGroupStates.Error("Something went wrong")
            }
        }
    }
}