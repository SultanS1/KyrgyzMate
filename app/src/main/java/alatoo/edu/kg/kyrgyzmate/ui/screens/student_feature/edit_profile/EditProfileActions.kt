package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.edit_profile

import alatoo.edu.kg.kyrgyzmate.core.BaseAction
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem

sealed interface EditProfileActions : BaseAction {

    data object LoadData : EditProfileActions

    data class SubmitData(
        val firstName: String,
        val lastName: String,
        val group: GroupItem?
    ) : EditProfileActions

    data object Cancel : EditProfileActions
}