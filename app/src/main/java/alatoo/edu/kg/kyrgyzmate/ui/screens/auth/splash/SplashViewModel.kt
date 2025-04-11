package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserLocalRepository

class SplashViewModel(
    private val userLocalRepository: UserLocalRepository,
) : BaseViewModel<SplashStates, SplashActions>(SplashStates.Loading) {

    override fun submitAction(action: SplashActions) {
        when (action) {
            is SplashActions.LoadData -> checkForAuthorized()
        }
    }

    private fun checkForAuthorized() {
        when(userLocalRepository.isUserLoggedIn()) {
            UserRole.STUDENT -> _state.value = SplashStates.UserIsStudent
            UserRole.LECTURER -> _state.value = SplashStates.UserIsLecturer
            UserRole.UNKOWN -> _state.value = SplashStates.UserNotAuthorized
        }
    }
}