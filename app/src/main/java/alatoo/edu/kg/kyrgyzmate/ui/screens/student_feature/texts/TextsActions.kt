package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.texts

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface TextsActions : BaseAction {

    data class LoadData(val id: String) : TextsActions
}