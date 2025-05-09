package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.new_group

import alatoo.edu.kg.kyrgyzmate.core.BaseState

sealed interface CreateNewGroupStates : BaseState {

    data object Loading : CreateNewGroupStates

    data class Error(val message: String) : CreateNewGroupStates

    data object Success : CreateNewGroupStates

    data class GroupFieldsState(
        val nameState: String? = null,
        val descriptionState: String? = null
    ) : CreateNewGroupStates

    data object Popup : CreateNewGroupStates
}