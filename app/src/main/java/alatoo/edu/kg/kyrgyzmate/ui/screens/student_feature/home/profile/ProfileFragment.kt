package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentProfileBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class ProfileFragment : BaseFragment<FragmentStudentProfileBinding>(R.layout.fragment_student_profile) {
    override val binding by viewBinding(FragmentStudentProfileBinding::bind)
}