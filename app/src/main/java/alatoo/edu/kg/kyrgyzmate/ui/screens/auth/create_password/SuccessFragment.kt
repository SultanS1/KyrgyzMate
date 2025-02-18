package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentSuccessBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateAndClearBackStack
import alatoo.edu.kg.kyrgyzmate.extensions.navigateWithPopUp
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class SuccessFragment : BaseFragment<FragmentSuccessBinding>(R.layout.fragment_success) {

    override val binding: FragmentSuccessBinding by viewBinding(FragmentSuccessBinding::bind)

    override fun setupUI() {
        super.setupUI()
        with(binding) {
            okButton.setClickListener {
                findNavController().navigateWithPopUp(R.id.loginFragment, R.id.loginFragment, true)
            }
        }
    }
}