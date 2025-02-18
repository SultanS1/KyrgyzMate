package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class StudentFragment : BaseFragment<FragmentStudentBinding>(R.layout.fragment_student) {

    override val binding by viewBinding(FragmentStudentBinding::bind)
}