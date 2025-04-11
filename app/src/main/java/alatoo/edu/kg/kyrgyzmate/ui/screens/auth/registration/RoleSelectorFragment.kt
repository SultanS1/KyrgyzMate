package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentRoleSelectorBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class RoleSelectorFragment : Fragment(R.layout.fragment_role_selector) {

    private val binding by viewBinding(FragmentRoleSelectorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            studentButton.pressCompressInAnimation()
            studentButton.setClickListener {
                val action = RoleSelectorFragmentDirections.actionRoleSelectorFragmentToRegistrationFragment(UserRole.STUDENT)
                findNavController().navigateToWithSafeArgs(action)
            }
            lecturerButton.pressCompressInAnimation()
            lecturerButton.setClickListener {
                val action = RoleSelectorFragmentDirections.actionRoleSelectorFragmentToRegistrationFragment(UserRole.LECTURER)
                findNavController().navigateToWithSafeArgs(action)
            }
        }
    }
}