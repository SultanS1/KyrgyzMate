package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLecturerProfileBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showTwoActionDialog
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LecturerProfileFragment
    : BaseFragment<FragmentLecturerProfileBinding, LecturerProfileStates, LecturerProfileActions>(R.layout.fragment_lecturer_profile) {

    override val binding by viewBinding(FragmentLecturerProfileBinding::bind)

    override val viewModel by viewModel<LecturerProfileViewModel>()

    override fun setupUI() {
        viewModel.submitAction(LecturerProfileActions.Load)
        binding.logoutButton.setClickListener {
            requireContext().showTwoActionDialog(
                "You want to logout?",
                "Logout",
                "Cancel",
                { viewModel.submitAction(LecturerProfileActions.Logout) },
                { it.dismiss() }
            )
        }
    }

    override fun renderState(state: LecturerProfileStates) {
        when(state) {
            is LecturerProfileStates.Error -> showErrorDialog(R.string.error_unknown_error)
            LecturerProfileStates.Loading -> {}
            LecturerProfileStates.Logout -> {
                requireParentFragment().requireParentFragment().requireParentFragment().requireParentFragment().findNavController().navigateAndClearBackStack(R.id.splashFragment)
            }
            is LecturerProfileStates.ShowProfileInfo -> {
                binding.fullNameTextView.text = state.user.name + " " + state.user.surname
                binding.firstNameTextView.text = state.user.name
                binding.lastNameTextView.text = state.user.surname
                binding.emailTextView.text = state.user.email
            }
        }
    }
}