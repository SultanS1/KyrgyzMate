package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentProfileBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showTwoActionDialog
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.HomeFragmentDirections
import android.provider.ContactsContract.Profile
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
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
            editProfileButton.setClickListener {
                viewModel.submitAction(ProfileActions.ShowEditProfile)
            }
            logoutButton.setClickListener {
                requireContext().showTwoActionDialog(
                    "You want to logout?",
                    "Logout",
                    "Cancel",
                    { viewModel.submitAction(ProfileActions.Logout) },
                    { it.dismiss() }
                )
            }
        }
    }

    override fun renderState(state: ProfileStates) {
        when (state) {
            is ProfileStates.Loading -> {
                with(binding) {
                    fullNameTextView.isVisible = false
                    roleTextView.isVisible = false
                    contentContainer.isVisible = false
                    shimmerView.isVisible = true
                }
            }
            is ProfileStates.ShowProfile -> renderProfile(state.profile)
            is ProfileStates.Error -> showErrorDialog(R.string.error_unknown_error)
            is ProfileStates.ShowEditProfile -> {
                val action = HomeFragmentDirections.actionHomeFragmentToEditProfileFragment(state.profile)
                requireParentFragment().requireParentFragment().findNavController().navigateToWithSafeArgs(action)
            }

            ProfileStates.Logout ->
                requireParentFragment().requireParentFragment().requireParentFragment().requireParentFragment().findNavController().navigateAndClearBackStack(R.id.splashFragment)
        }
    }

    private fun renderProfile(profile: StudentProfile) {
        with(binding) {
            fullNameTextView.isVisible = true
            roleTextView.isVisible = true
            contentContainer.isVisible = true
            shimmerView.isVisible = false

            fullNameTextView.text = profile.getFullName()
            roleTextView.text = profile.role?.name
            firstNameTextView.text = profile.name
            lastNameTextView.text = profile.surname
            emailTextView.text = profile.email
            groupTextView.text = profile.studentGroupInfo?.groupInfo
            lastTopicPassedTextView.text = profile.studentGroupInfo?.lastTopic
        }
    }
}