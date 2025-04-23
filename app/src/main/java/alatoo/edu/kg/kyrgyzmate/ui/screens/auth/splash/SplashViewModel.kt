package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.domain.user.UserInteractor

class SplashViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel<SplashStates, SplashActions>(SplashStates.Loading) {

    override fun submitAction(action: SplashActions) {
        when (action) {
            is SplashActions.LoadData -> checkForAuthorized()
        }
    }

    private fun checkForAuthorized() {
        launch {
            if(userInteractor.userSession()) {
                when(userInteractor.getUserRole()) {
                    UserRole.STUDENT -> _state.value = SplashStates.UserIsStudent
                    UserRole.LECTURER -> _state.value = SplashStates.UserIsLecturer
                    UserRole.UNKOWN -> _state.value = SplashStates.UserNotAuthorized
                }
            } else {
                _state.value = SplashStates.UserNotAuthorized
            }
        }
    }
}