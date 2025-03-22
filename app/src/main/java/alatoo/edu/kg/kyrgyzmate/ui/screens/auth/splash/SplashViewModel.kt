package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel

class SplashViewModel : BaseViewModel<SplashStates, SplashActions>(SplashStates.Loading) {

    override fun submitAction(action: SplashActions) {
        when (action) {
            is SplashActions.LoadData -> checkForAuthorized()
        }
    }

    private fun checkForAuthorized() {
        _state.value = SplashStates.UserNotAuthorized
    }
}