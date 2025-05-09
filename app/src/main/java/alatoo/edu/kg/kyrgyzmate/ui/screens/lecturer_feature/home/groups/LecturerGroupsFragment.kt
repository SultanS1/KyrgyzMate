package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.groups

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLecturerGroupsBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateTo
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showLoadingDialog
import alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.LecturerHomeFragmentDirections
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes.ThemesFragmentDirections
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LecturerGroupsFragment
    : BaseFragment<FragmentLecturerGroupsBinding, GroupsStates, GroupsActions>(R.layout.fragment_lecturer_groups) {

    override val binding by viewBinding(FragmentLecturerGroupsBinding::bind)

    override val viewModel by viewModel<GroupsViewModel>()

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(GroupsAdapter{
                viewModel.submitAction(GroupsActions.NavigateToDetails(it))
            })
            .build()
    }

    override fun setupUI() {
        with(binding) {
            viewModel.submitAction(GroupsActions.LoadData)
            groupsRecyclerView.adapter = adapter
            createGroupButton.setClickListener { viewModel.submitAction(GroupsActions.CreateNewGroup) }
            createGroupButton.pressCompressInAnimation()
        }
    }

    override fun renderState(state: GroupsStates) {
        when(state) {
            is GroupsStates.Error -> showErrorDialog(R.string.error_unknown_error)
            is GroupsStates.Loading -> {
                binding.shimmerView.isVisible = true
                binding.groupsRecyclerView.isVisible = false
                binding.emptyStatusTextView.isVisible = false
            }
            is GroupsStates.ShowCreateNewGroup -> requireParentFragment().requireParentFragment().findNavController().navigateTo(R.id.createNewGroupFragment)
            is GroupsStates.ShowEmptyScreen -> {
                binding.shimmerView.isVisible = false
                binding.groupsRecyclerView.isVisible = false
                binding.emptyStatusTextView.isVisible = true
            }
            is GroupsStates.ShowGroups ->{
                adapter.submitList(state.groups)
                binding.shimmerView.isVisible = false
                binding.groupsRecyclerView.isVisible = true
                binding.emptyStatusTextView.isVisible = false
            }
            is GroupsStates.ShowGroupsDetails -> {
                val action = LecturerHomeFragmentDirections.actionLecturerHomeFragmentToLecturerGroupFragment2(state.group)
                requireParentFragment().requireParentFragment().findNavController().navigateToWithSafeArgs(action)
            }
        }
    }
}