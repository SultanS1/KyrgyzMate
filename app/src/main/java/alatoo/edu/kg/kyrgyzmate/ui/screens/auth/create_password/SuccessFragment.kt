package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentSuccessBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateWithPopUp
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class SuccessFragment : Fragment(R.layout.fragment_success) {

    private val binding: FragmentSuccessBinding by viewBinding(FragmentSuccessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            okButton.setClickListener {
                findNavController().navigateWithPopUp(R.id.loginFragment, R.id.loginFragment, true)
            }
        }
    }
}