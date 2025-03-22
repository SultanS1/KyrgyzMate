package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.core.BaseState

sealed interface SplashStates : BaseState {
    data object Loading: SplashStates
    data object UserNotAuthorized: SplashStates
    data object UserIsStudent: SplashStates
    data object UserIsLecturer: SplashStates
}