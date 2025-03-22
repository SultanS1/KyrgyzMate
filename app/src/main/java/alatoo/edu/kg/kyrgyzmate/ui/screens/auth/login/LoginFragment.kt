package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLoginBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showTwoActionDialog
import android.content.Intent
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginPageState, LoginPageActions>(R.layout.fragment_login) {

    override val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override val viewModel by viewModel<LoginViewModel>()

    override fun setupUI() {
        with(binding) {
            loginButton.pressCompressInAnimation()
            loginButton.setClickListener {
                viewModel.submitAction(
                    LoginPageActions.SubmitData(
                        emailEditText.text.toString(), passwordEditText.text.toString()
                    )
                )
            }
            signUpButton.setClickListener {
                viewModel.submitAction(LoginPageActions.Register)
            }
            forgotPasswordButton.setClickListener {
                viewModel.submitAction(LoginPageActions.ForgotPassword)
            }
        }
    }

    override fun renderState(state: LoginPageState) {
        with(binding) {
            when (state) {
                is LoginPageState.LoginFieldsState -> {
                    emailContainer.error = state.emailEmpty
                    emailContainer.isErrorEnabled = state.emailEmpty != null

                    passwordContainer.error = state.passwordEmpty
                    passwordContainer.isErrorEnabled = state.passwordEmpty != null
                }

                is LoginPageState.RoleStudent -> findNavController().navigateAndClearBackStack(R.id.studentFragment)

                is LoginPageState.RoleLecturer -> findNavController().navigateAndClearBackStack(R.id.lecturerFragment)

                is LoginPageState.ForgotPassword -> requireContext().showTwoActionDialog(
                    title = getString(R.string.title_deeplink_to_pass_generation),
                    positiveActionText = getString(R.string.action_open_gmail),
                    negativeActionText = getString(R.string.action_resend),
                    positiveAction = {},
                    negativeAction = {}
                )

                is LoginPageState.Register -> findNavController().navigateTo(R.id.action_loginFragment_to_roleSelectorFragment)
            }
        }
    }
}