package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLoginBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.content.res.ColorStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel by viewModel<LoginViewModel>()

    override fun setupUI() {
        super.setupUI()
        with(binding) {
            loginButton.pressCompressInAnimation()
            loginButton.setClickListener {
                viewModel.loginAction(emailEditText.text.toString(), passwordEditText.text.toString())
            }
            signUpButton.setClickListener {
                findNavController().navigateTo(R.id.action_loginFragment_to_roleSelectorFragment)
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.fieldsState.collect() {
                        fieldsState(it)
                    }
                }
            }
        }
    }

    private fun fieldsState(state: LoginPageState) {
        with(binding) {
            when(state) {
                is LoginPageState.LoginFieldsState -> {
                    emailEditText.error = state.emailEmpty
                    passwordEditText.error = state.passwordEmpty
                }
                is LoginPageState.RoleStudent -> findNavController().navigateAndClearBackStack(R.id.action_loginFragment_to_studentFragment)
                is LoginPageState.RoleLecturer -> findNavController().navigateAndClearBackStack(R.id.action_loginFragment_to_lecturerFragment)
            }
        }
    }
}