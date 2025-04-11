package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLoginBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showLoadingDialog
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import alatoo.edu.kg.kyrgyzmate.extensions.showTwoActionDialog
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginPageState, LoginPageActions>(R.layout.fragment_login) {

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
                viewModel.submitAction(
                    LoginPageActions.ForgotPassword(emailEditText.text.toString())
                )
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

                is LoginPageState.ForgotPassword -> requireContext().showTwoActionDialog(title = getString(
                    R.string.title_deeplink_to_pass_registration
                ),
                    positiveActionText = getString(R.string.action_open_gmail),
                    negativeActionText = getString(R.string.action_resend),
                    positiveAction = {},
                    negativeAction = {})

                is LoginPageState.Register -> findNavController().navigateTo(R.id.roleSelectorFragment)
                is LoginPageState.Error -> showErrorDialog(state.messageRes)
                LoginPageState.Loading -> requireContext().showLoadingDialog(viewLifecycleOwner.lifecycle)
                LoginPageState.OpenGmail -> showLinkSentDialog()
            }
        }
    }

    private fun showLinkSentDialog() {
        requireContext().showOneActionDialog(
            title = getString(R.string.title_link_to_reset_password),
            actionText = getString(R.string.action_open_gmail)
        ) {
            val intent =
                requireContext().packageManager.getLaunchIntentForPackage("com.google.android.gm")
            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Gmail app not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showErrorDialog(messageRes: Int) {
        requireContext().showOneActionDialog(
            getString(messageRes), getString(R.string.action_ok)
        ) {
            it.dismiss()
        }
    }
}