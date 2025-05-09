package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.edit_profile

import alatoo.edu.kg.kyrgyzmate.core.BaseState
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem

sealed interface EditProfileStates : BaseState {

    data class ProfileFields(
        val firstNameState: String? = null,
        val lastNameState: String? = null,
    ) : EditProfileStates

    data class ShowProfileData(val groupOptions: List<GroupItem>, ) : EditProfileStates

    data object Loading : EditProfileStates

    data class Error(val messageRes: Int) : EditProfileStates

    data object NavigateBack : EditProfileStates
}