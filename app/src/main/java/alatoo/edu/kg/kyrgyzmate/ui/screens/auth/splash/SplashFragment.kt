package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentAuthSplashBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment :
    BaseFragment<FragmentAuthSplashBinding, SplashStates, SplashActions>(R.layout.fragment_auth_splash) {

    override val binding by viewBinding(FragmentAuthSplashBinding::bind)
    override val viewModel by viewModel<SplashViewModel>()

    override fun setupUI() {
        with(binding) {
            root.alpha = 0f
            root.animate().setDuration(1500).alpha(1f).withEndAction {
                viewModel.submitAction(SplashActions.LoadData)
            }
        }
    }

    override fun renderState(state: SplashStates) {
        when (state) {
            SplashStates.UserIsLecturer -> {
                findNavController().navigateAndClearBackStack(R.id.lecturerFragment)
            }

            SplashStates.UserIsStudent -> {
                findNavController().navigateAndClearBackStack(R.id.studentFragment)
            }

            SplashStates.UserNotAuthorized -> {
                findNavController().navigateAndClearBackStack(R.id.loginFragment)
            }

            SplashStates.Loading -> {}
        }
    }
}
