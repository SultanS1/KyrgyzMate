package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentCreatePasswordBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class CreatePasswordFragment : BaseFragment<FragmentCreatePasswordBinding>(R.layout.fragment_create_password) {

    override val binding: FragmentCreatePasswordBinding by viewBinding(FragmentCreatePasswordBinding::bind)

    override fun setupUI() {
        super.setupUI()
        with(binding) {
            createPasswordButton.setClickListener {
                findNavController().navigateTo(R.id.action_createPasswordFragment_to_successFragment)
            }
        }
    }
}