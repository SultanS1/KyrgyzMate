package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.words

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentWordsBinding
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsFragment
    : BaseFragment<FragmentStudentWordsBinding, WordsStates, WordsActions>(R.layout.fragment_student_words){

    override val binding by viewBinding(FragmentStudentWordsBinding::bind)

    override val viewModel by viewModel<WordsViewModel>()

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(
                WordsAdapter {
                    viewModel.submitAction(WordsActions.AudioPlayed(it))
                }
            )
            .build()
    }

    override fun setupUI() {
        val args: WordsFragmentArgs by navArgs()
        val id = args.id ?: ""
        with(binding) {
            viewModel.submitAction(WordsActions.LoadWords(id))
            arrowBackButton.setClickListener { findNavController().navigateUp() }
            recyclerView.adapter = adapter
        }
    }

    override fun renderState(state: WordsStates) {
        when(state) {
            is WordsStates.Loading -> {
                binding.recyclerView.isVisible = false
                binding.shimmerView.isVisible = true
            }
            is WordsStates.ShowWords -> {
                adapter.submitList(state.words)
                binding.recyclerView.isVisible = true
                binding.shimmerView.isVisible = false
            }
            is WordsStates.UpdatePlayedAudio -> {
                adapter.notifyItemChanged(state.position)
            }
            is WordsStates.Error -> showErrorDialog(R.string.error_unknown_error)
        }
    }
}