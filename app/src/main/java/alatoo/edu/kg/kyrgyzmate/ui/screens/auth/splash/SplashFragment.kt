package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentAuthSplashBinding
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class SplashFragment : BaseFragment<FragmentAuthSplashBinding>(R.layout.fragment_auth_splash) {

    override val binding: FragmentAuthSplashBinding by viewBinding(FragmentAuthSplashBinding::bind)

    override fun setupUI() {
        super.setupUI()
        with(binding) {
            root.alpha = 0f
            root.animate().setDuration(1500).alpha(1f).withEndAction {
                findNavController().navigate(R.id.action_splashFragment_to_registrationFragment)
            }
        }
    }
}
