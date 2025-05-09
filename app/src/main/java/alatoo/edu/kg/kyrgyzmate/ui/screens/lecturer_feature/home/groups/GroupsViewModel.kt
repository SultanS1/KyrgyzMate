package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.groups

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.domain.lecturer.LecturerInteractor
import android.util.Log

class GroupsViewModel(
    private val lecturerInteractor: LecturerInteractor
) : BaseViewModel<GroupsStates, GroupsActions>(initialState = GroupsStates.Loading) {

    override fun submitAction(action: GroupsActions) {
        when(action) {
            is GroupsActions.LoadData -> loadGroups()
            is GroupsActions.NavigateToDetails -> _state.value =
                GroupsStates.ShowGroupsDetails(action.group)
            is GroupsActions.CreateNewGroup -> _state.value = GroupsStates.ShowCreateNewGroup
        }
    }

    private fun loadGroups() {
        launch {
            _state.value = GroupsStates.Loading
            val result = lecturerInteractor.getLecturerGroups()
            when (result) {
                is FirebaseGetResponse.Success -> {
                    if (result.data.isNotEmpty()) {
                        _state.value = GroupsStates.ShowGroups(result.data)
                    } else {
                        _state.value = GroupsStates.ShowEmptyScreen
                    }
                }
                is FirebaseGetResponse.Error -> {
                    _state.value = GroupsStates.Error(result.errorMessage)
                }
            }
        }
    }
}