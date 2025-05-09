package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentThemesBinding
import alatoo.edu.kg.kyrgyzmate.extensions.navigateToWithSafeArgs
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment
    : BaseFragment<FragmentThemesBinding, TopicsStates, TopicsActions>(R.layout.fragment_themes) {

    override val binding by viewBinding(FragmentThemesBinding::bind)

    override val viewModel by viewModel<TopicsViewModel>()

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(TopicsAdapter {
                viewModel.submitAction(TopicsActions.OpenTopic(it.id))
            } )
            .build()
    }

    private var theme: String = ""

    override fun setupUI() {
        val args: TopicsFragmentArgs by navArgs()
        val themeType = args.themeType
        theme = themeType?.name ?: ""

        with(binding) {
            titleTextView.text = theme
            viewModel.submitAction(TopicsActions.LoadThemes(themeType?.id ?: ""))
            recyclerView.adapter = adapter
            arrowBackButton.setClickListener { viewModel.submitAction(TopicsActions.Popup) }
        }
    }

    override fun renderState(state: TopicsStates) {
        when(state) {
            is TopicsStates.Loading -> {
                binding.shimmerViewTopics.isVisible = true
                binding.recyclerView.isVisible = false
            }
            is TopicsStates.Popup -> findNavController().navigateUp()
            is TopicsStates.ShowTopics -> {
                adapter.submitList(state.topics)
                binding.shimmerViewTopics.isVisible = false
                binding.recyclerView.isVisible = true
            }
            is TopicsStates.Error -> showErrorDialog(R.string.error_unknown_error)

            is TopicsStates.NavigateToTopic -> {
                when(theme) {
                    "Dialogs" -> {
                        val action = TopicsFragmentDirections.actionTopicsFragmentToDialogFragment(state.id)
                        findNavController().navigateToWithSafeArgs(action)
                    }
                    "Words" -> {
                        val action = TopicsFragmentDirections.actionTopicsFragmentToWordsFragment(state.id)
                        findNavController().navigateToWithSafeArgs(action)
                    }
                    "Texts" -> {
                        val action = TopicsFragmentDirections.actionTopicsFragmentToTextsFragment(state.id)
                        findNavController().navigateToWithSafeArgs(action)
                    }
                    "Grammar" -> {
                        val action = TopicsFragmentDirections.actionTopicsFragmentToGrammarFragment(state.id)
                        findNavController().navigateToWithSafeArgs(action)
                    }
                }
            }
        }
    }
}