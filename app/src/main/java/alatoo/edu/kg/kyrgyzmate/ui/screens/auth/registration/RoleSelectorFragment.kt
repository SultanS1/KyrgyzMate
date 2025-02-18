package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentRoleSelectorBinding
import alatoo.edu.kg.kyrgyzmate.domain.model.role.UserRole
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class RoleSelectorFragment : BaseFragment<FragmentRoleSelectorBinding>(R.layout.fragment_role_selector) {

    override val binding by viewBinding(FragmentRoleSelectorBinding::bind)

    override fun setupUI() {
        super.setupUI()
        with(binding) {
            studentButton.setClickListener {
                val action = RoleSelectorFragmentDirections.actionRoleSelectorFragmentToRegistrationFragment(UserRole.STUDENT)
                findNavController().navigateToWithSafeArgs(action)
            }
            lecturerButton.setClickListener {
                val action = RoleSelectorFragmentDirections.actionRoleSelectorFragmentToRegistrationFragment(UserRole.LECTURER)
                findNavController().navigateToWithSafeArgs(action)
            }
        }
    }

}