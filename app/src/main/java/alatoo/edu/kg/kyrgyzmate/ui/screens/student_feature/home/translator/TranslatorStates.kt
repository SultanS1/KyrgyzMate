package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.translator

import alatoo.edu.kg.kyrgyzmate.core.BaseState

sealed interface TranslatorStates : BaseState {

    data class Translator(
        val translatedText: String = "",
    ) : TranslatorStates
}