package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentRegistrationBinding
import alatoo.edu.kg.kyrgyzmate.domain.model.role.UserRole
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding, RegistrationPageStates, RegistrationPageAction>(R.layout.fragment_registration) {

    override val binding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    override val viewModel by viewModel<RegistrationViewModel>()

    override fun setupUI() {
        val args: RegistrationFragmentArgs by navArgs()
        val userRole = args.role

        with(binding) {
            groupSelectContainer.isVisible = userRole == UserRole.STUDENT
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

                RegistrationPageStates.Register -> {
                    // TODO: OPEN DIALOG WHICH WILL SUGGEST TO OPEN GMAIL TO GET DEEPLINK(Will be replaced later
                    findNavController().navigateTo(R.id.action_registrationFragment_to_createPasswordFragment)
                }

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
            }
        }
    }
}