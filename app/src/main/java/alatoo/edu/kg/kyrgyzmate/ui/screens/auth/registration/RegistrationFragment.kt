package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentRegistrationBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(R.layout.fragment_registration) {

    override val binding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)

}