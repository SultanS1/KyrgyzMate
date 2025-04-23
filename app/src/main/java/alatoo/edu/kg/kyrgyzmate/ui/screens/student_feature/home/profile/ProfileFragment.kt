package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentProfileBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment
    : BaseFragment<FragmentStudentProfileBinding, ProfileStates, ProfileActions>(R.layout.fragment_student_profile) {

    override val binding by viewBinding(FragmentStudentProfileBinding::bind)

    override val viewModel by viewModel<ProfileViewModel>()

    override fun setupUI() {
        with(binding) {
            viewModel.submitAction(ProfileActions.LoadProfile())
            logoutButton.pressCompressInAnimation()
            editProfileButton.pressCompressInAnimation()
        }
    }

    override fun renderState(state: ProfileStates) {
        when (state) {
            is ProfileStates.Loading -> {}
            is ProfileStates.ShowProfile -> renderProfile(state.profile)
            is ProfileStates.Error -> {}
        }
    }

    private fun renderProfile(profile: StudentProfile) {
        with(binding) {
            fullNameTextView.text = profile.getFullName()
            roleTextView.text = profile.role?.name
            firstNameTextView.text = profile.name
            lastNameTextView.text = profile.surname
            emailTextView.text = profile.email
            groupTextView.text = profile.group
        }
    }
}