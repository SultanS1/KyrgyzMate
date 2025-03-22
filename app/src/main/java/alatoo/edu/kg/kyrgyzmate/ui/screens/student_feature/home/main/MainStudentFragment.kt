package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentMainStudentBinding
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding

class MainStudentFragment : Fragment(R.layout.fragment_main_student) {
    private val binding by viewBinding(FragmentMainStudentBinding::bind)

    private val adapter = TestAdapter()
}