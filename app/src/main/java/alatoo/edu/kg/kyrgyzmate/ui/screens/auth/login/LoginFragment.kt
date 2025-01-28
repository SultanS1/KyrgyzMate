package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLoginBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override fun setupUI() {
        super.setupUI()
        with(binding) {
            loginButton.pressCompressInAnimation()
            signUpButton.setClickListener {  }
        }
    }
}