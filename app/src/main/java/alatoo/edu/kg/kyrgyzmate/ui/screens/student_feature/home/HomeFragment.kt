package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentHomeFeatureBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class HomeFragment : Fragment(R.layout.fragment_student_home_feature) {

    private val binding by viewBinding(FragmentStudentHomeFeatureBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController =
            (childFragmentManager.findFragmentById(R.id.studentHomeFeatureFragmentContainer) as NavHostFragment)
                .navController
        val bottomNavigationView = binding.studentHomeFeatureBottomNavigationView

        bottomNavigationView.setupWithNavController(navController)
    }
}