package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.core.BaseAction

sealed interface SplashActions : BaseAction {
    data object LoadData: SplashActions
}