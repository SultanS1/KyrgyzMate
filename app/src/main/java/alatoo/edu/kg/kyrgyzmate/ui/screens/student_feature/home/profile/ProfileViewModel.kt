package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.domain.student.StudentInteractor
import alatoo.edu.kg.kyrgyzmate.domain.user.UserInteractor
import android.util.Log

class ProfileViewModel(
    private val studentInteractor: StudentInteractor,
    private val userInteractor: UserInteractor
) : BaseViewModel<ProfileStates, ProfileActions>(initialState = ProfileStates.Loading){

    private var profile: StudentProfile? = null

    override fun submitAction(action: ProfileActions) {
        when(action) {
            is ProfileActions.LoadProfile -> loadProfile(action.refresh)
            is ProfileActions.ShowEditProfile -> profile?.let { _state.value = ProfileStates.ShowEditProfile(it) }
            is ProfileActions.Logout -> logout()
        }
    }

    private fun loadProfile(refresh: Boolean) {
        launch {
            _state.value = ProfileStates.Loading
            val result = studentInteractor.getUserProfile(refresh)
            when(result) {
                is FirebaseGetResponse.Error -> {
                    _state.value = ProfileStates.Error(result.errorMessage)
                }

                is FirebaseGetResponse.Success -> {
                    profile = result.data
                    _state.value = ProfileStates.ShowProfile(result.data ?: StudentProfile())
                    Log.d("ProfileViewModel", "loadProfile: ${result.data}")
                }
            }
        }
    }

    private fun logout() {
        launch {
            _state.value = ProfileStates.Loading
            val result = userInteractor.signOut()
            when(result) {
                FireBasePostResponse.SUCCESS -> {
                    _state.value = ProfileStates.Logout
                }
                else -> {
                    _state.value = ProfileStates.Error(result.name)
                }
            }
        }
    }
}