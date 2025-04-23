package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.ThemeType
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentThemesBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThemesFragment : BaseFragment<FragmentThemesBinding, ThemesStates, ThemesActions>(R.layout.fragment_themes) {

    override val binding by viewBinding(FragmentThemesBinding::bind)

    override val viewModel by viewModel<ThemesViewModel>()

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(ThemesAdapter {
                viewModel.submitAction(ThemesActions.OpenTopics(ThemeType(it.name, it.id)))
            })
            .build()
    }

    override fun setupUI() {
        val args: ThemesFragmentArgs by navArgs()
        val parentInfo = args.parentInfo

        with(binding) {
            titleTextView.text = parentInfo?.name ?: "Theme"
            viewModel.submitAction(ThemesActions.LoadThemes(parentInfo?.id ?: ""))
            recyclerView.adapter = adapter
            arrowBackButton.setClickListener { viewModel.submitAction(ThemesActions.Popup) }
        }
    }

    override fun renderState(state: ThemesStates) {
        when(state) {
            is ThemesStates.Error -> {
                requireContext().showOneActionDialog(
                    "Error: ${state.message}", "Ok"
                ) {
                    it.dismiss()
                }
            }
            is ThemesStates.Loading -> {
                binding.recyclerView.isVisible = false
                binding.shimmerView.isVisible = true
            }
            is ThemesStates.NavigateToTopics -> {
                val action = ThemesFragmentDirections.actionThemesFragmentToTopicsFragment(state.themeType)
                findNavController().navigateToWithSafeArgs(action)
            }
            is ThemesStates.Popup -> findNavController().navigateUp()
            is ThemesStates.ShowThemes -> {
                adapter.submitList(state.themes)
                binding.recyclerView.isVisible = true
                binding.shimmerView.isVisible = false
            }
        }
    }
}