package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentMainStudentBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.HomeFragmentDirections
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics.TopicsAdapter
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainStudentFragment
    : BaseFragment<FragmentMainStudentBinding, MainStudentStates, MainStudentActions>(R.layout.fragment_main_student) {

    override val binding by viewBinding(FragmentMainStudentBinding::bind)

    override val viewModel by viewModel<MainStudentViewModel>()

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(LevelsAdapter {
                viewModel.submitAction(MainStudentActions.OpenCourses(it))
            }).build()
    }

    override fun setupUI() {
        with(binding) {
            viewModel.submitAction(MainStudentActions.LoadData)
            coursesRecyclerView.adapter = adapter
        }
    }

    override fun renderState(state: MainStudentStates) {
        when(state) {
            is MainStudentStates.Loading -> {
                binding.shimmerView.isVisible = true
                binding.coursesRecyclerView.isVisible = false
            }

            is MainStudentStates.EnterCourse -> {
                val action = HomeFragmentDirections.actionHomeFragmentToThemesFragment(state.folderInfo)
                requireParentFragment().requireParentFragment().findNavController().navigateToWithSafeArgs(action)
            }
            is MainStudentStates.ShowLevels -> {
                binding.shimmerView.isVisible = false
                binding.coursesRecyclerView.isVisible = true
                adapter.submitList(state.levels)
            }

            is MainStudentStates.Error -> showErrorDialog(R.string.error_unknown_error)

        }
    }
}