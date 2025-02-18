package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentRegistrationBinding
import alatoo.edu.kg.kyrgyzmate.domain.model.role.UserRole
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(R.layout.fragment_registration) {

    override val binding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel by viewModel<RegistrationViewModel>()

    override fun setupUI() {
        super.setupUI()
        val args: RegistrationFragmentArgs by navArgs()
        val userRole = args.role

        with(binding) {
            groupSelectContainer.isVisible = userRole == UserRole.STUDENT
            signUpButton.setClickListener {
                findNavController().navigateTo(R.id.action_registrationFragment_to_createPasswordFragment)
            }
            arrowBackButton.setClickListener {
                findNavController().popBackStack()
            }
        }
    }
}