package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.groups

import alatoo.edu.kg.kyrgyzmate.core.BaseAction
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem

sealed interface GroupsActions : BaseAction {

    data object LoadData : GroupsActions

    data class NavigateToDetails(val group: GroupItem) : GroupsActions

    data object CreateNewGroup : GroupsActions
}