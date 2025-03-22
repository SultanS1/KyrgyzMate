package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentCreatePasswordBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePasswordFragment :
    BaseFragment<FragmentCreatePasswordBinding, CreatePasswordStates, CreatePasswordActions>(R.layout.fragment_create_password) {

    override val binding: FragmentCreatePasswordBinding by viewBinding(FragmentCreatePasswordBinding::bind)
    override val viewModel by viewModel<CreatePasswordViewModel>()

    override fun setupUI() {
        with(binding) {
            createPasswordButton.setClickListener {
                viewModel.submitAction(CreatePasswordActions.CreatePassword(
                    password = passwordEditText.text.toString(),
                    confirmPassword = confirmPasswordEditText.text.toString()
                ))
            }
        }
    }

    override fun renderState(state: CreatePasswordStates) {
        with(binding) {
            when(state) {
                is CreatePasswordStates.CreatePassword -> {
                    findNavController().navigateTo(R.id.action_createPasswordFragment_to_successFragment)
                }

                is CreatePasswordStates.PasswordFields -> {
                    passwordContainer.error = state.passwordField
                    passwordContainer.isErrorEnabled = state.passwordField != null

                    confirmPasswordContainer.error = state.confirmPasswordField
                    confirmPasswordContainer.isErrorEnabled = state.confirmPasswordField != null
                }
            }
        }
    }
}