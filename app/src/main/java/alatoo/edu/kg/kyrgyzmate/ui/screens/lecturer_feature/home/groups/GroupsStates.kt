package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.groups

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem

sealed interface GroupsStates : BaseState {

    data object Loading : GroupsStates

    data class Error(val message: String) : GroupsStates

    data class ShowGroups(val groups: List<GroupItem>) : GroupsStates

    data object ShowEmptyScreen : GroupsStates

    data class ShowGroupsDetails(val group: GroupItem) : GroupsStates

    data object ShowCreateNewGroup : GroupsStates
}