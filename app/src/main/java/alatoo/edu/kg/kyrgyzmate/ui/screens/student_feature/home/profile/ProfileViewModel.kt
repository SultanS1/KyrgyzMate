package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.domain.student.StudentInteractor
import android.util.Log

class ProfileViewModel(
    private val studentInteractor: StudentInteractor
) : BaseViewModel<ProfileStates, ProfileActions>(initialState = ProfileStates.Loading){

    override fun submitAction(action: ProfileActions) {
        when(action) {
            is ProfileActions.LoadProfile -> loadProfile(action.refresh)
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
                    _state.value = ProfileStates.ShowProfile(result.data ?: StudentProfile())
                    Log.d("ProfileViewModel", "loadProfile: ${result.data}")
                }
            }
        }
    }
}