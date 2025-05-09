package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLecturerHomeFeatureBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class LecturerHomeFragment: Fragment(R.layout.fragment_lecturer_home_feature) {

    private val binding by viewBinding(FragmentLecturerHomeFeatureBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController =
            (childFragmentManager.findFragmentById(R.id.lecturerHomeFeatureFragmentContainer) as NavHostFragment)
                .navController
        val bottomNavigationView = binding.lecturerHomeFeatureBottomNavigationView

        bottomNavigationView.setupWithNavController(navController)
    }
}
