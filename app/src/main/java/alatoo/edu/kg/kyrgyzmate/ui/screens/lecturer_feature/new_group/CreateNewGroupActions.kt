package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.new_group

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface CreateNewGroupActions : BaseAction {

    data class SubmitData(
        val name: String,
        val description: String
    ) : CreateNewGroupActions

    data object Popup : CreateNewGroupActions
}