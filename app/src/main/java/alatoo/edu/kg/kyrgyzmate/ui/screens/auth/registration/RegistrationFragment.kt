package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentRegistrationBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showLoadingDialog
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding, RegistrationPageStates, RegistrationPageAction>(R.layout.fragment_registration) {

    override val binding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    override val viewModel by viewModel<RegistrationViewModel>()
    private var firstLaunch = true

    override fun setupUI() {
        val args: RegistrationFragmentArgs by navArgs()
        val userRole = args.role

        with(binding) {
            groupSelectContainer.isVisible = userRole == UserRole.STUDENT
            signUpButton.pressCompressInAnimation()
            signUpButton.setClickListener {
                viewModel.submitAction(
                    RegistrationPageAction.SubmitUserData(
                        firstName = firstNameEditText.text.toString(),
                        lastName = lastNameEditText.text.toString(),
                        group = groupEditText.text.toString(),
                        role = userRole,
                        email = emailEditText.text.toString()
                    )
                )
            }
            arrowBackButton.setClickListener {
                viewModel.submitAction(RegistrationPageAction.Popup)
            }
        }
    }

    override fun renderState(state: RegistrationPageStates) {
        with(binding) {
            when(state) {
                RegistrationPageStates.Popup -> findNavController().popBackStack()

                is RegistrationPageStates.RegistrationFieldsState -> {
                    firstNameContainer.error = state.firstNameState
                    firstNameContainer.isErrorEnabled = state.firstNameState != null

                    lastNameContainer.error = state.lastNameState
                    lastNameContainer.isErrorEnabled = state.lastNameState != null

                    groupContainer.error = state.groupState
                    groupContainer.isErrorEnabled = state.groupState != null

                    emailContainer.error = state.emailState
                    emailContainer.isErrorEnabled = state.emailState != null
                }

                is RegistrationPageStates.Error -> showErrorDialog(state.messageRes)

                RegistrationPageStates.Loading -> { requireContext().showLoadingDialog(viewLifecycleOwner.lifecycle) }

                RegistrationPageStates.ShowSuccess -> {
                    firstLaunch = false
                    showLinkSentDialog()
                }

                RegistrationPageStates.EmailVerified -> findNavController().navigateTo(R.id.action_registrationFragment_to_createPasswordFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!firstLaunch) {
            viewModel.submitAction(RegistrationPageAction.UserComeBack)
        }
    }

    private fun showLinkSentDialog() {
        requireContext().showOneActionDialog(
            title = getString(R.string.title_deeplink_to_pass_registration),
            actionText = getString(R.string.action_open_gmail)
        ) {
            val intent = requireContext().packageManager.getLaunchIntentForPackage("com.google.android.gm")
            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Gmail app not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showErrorDialog(messageRes: Int) {
        requireContext().showOneActionDialog(
            getString(messageRes),
            getString(R.string.action_ok))
        {
            it.dismiss()
        }
    }
}