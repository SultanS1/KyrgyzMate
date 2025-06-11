package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.domain.lecturer.LecturerInteractor
import alatoo.edu.kg.kyrgyzmate.domain.user.UserInteractor
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile.ProfileStates
import android.util.Log

class LecturerProfileViewModel(
    private val lecturerInteractor: LecturerInteractor,
    private val userInteractor: UserInteractor
) : BaseViewModel<LecturerProfileStates, LecturerProfileActions>(initialState = LecturerProfileStates.Loading) {
    override fun submitAction(action: LecturerProfileActions) {
        when(action) {
            LecturerProfileActions.Load -> loadProfile(false)
            LecturerProfileActions.Logout -> logout()
        }
    }

    private fun loadProfile(refresh: Boolean) {
        launch {
            _state.value = LecturerProfileStates.Loading
            val result = lecturerInteractor.getUserProfile(refresh)
            when(result) {
                is FirebaseGetResponse.Error -> {
                    _state.value = LecturerProfileStates.Error(result.errorMessage)
                }

                is FirebaseGetResponse.Success -> {
                    _state.value = LecturerProfileStates.ShowProfileInfo(result.data ?: User())
                    Log.d("ProfileViewModel", "loadProfile: ${result.data}")
                }
            }
        }
    }

    private fun logout() {
        launch {
            _state.value = LecturerProfileStates.Loading
            val result = userInteractor.signOut()
            when(result) {
                FireBasePostResponse.SUCCESS -> {
                    _state.value = LecturerProfileStates.Logout
                }
                else -> {
                    _state.value = LecturerProfileStates.Error(result.name)
                }
            }
        }
    }
}