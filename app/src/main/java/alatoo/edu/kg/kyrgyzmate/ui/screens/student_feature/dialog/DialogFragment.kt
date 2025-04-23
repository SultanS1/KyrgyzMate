package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.dialog

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentDialogBinding
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showOneActionDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogFragment
    : BaseFragment<FragmentStudentDialogBinding, DialogStates, DialogActions>(R.layout.fragment_student_dialog) {

    override val binding by viewBinding(FragmentStudentDialogBinding::bind)

    override val viewModel by viewModel<DialogViewModel>()

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(
                DialogAdapter {
                    viewModel.submitAction(DialogActions.AudioPlayed(it))
                }
            )
            .build()
    }

    override fun setupUI() {
        val args: DialogFragmentArgs by navArgs()
        val id = args.id ?: ""
        with(binding) {
            viewModel.submitAction(DialogActions.LoadData(id))
            recyclerView.adapter = adapter
            arrowBackButton.setClickListener { findNavController().navigateUp() }
        }
    }

    override fun renderState(state: DialogStates) {
        when(state) {
            is DialogStates.Loading -> {
                binding.shimmerView.isVisible = true
                binding.recyclerView.isVisible = false
            }
            is DialogStates.ShowData -> {
                adapter.submitList(state.dialogs)
                binding.shimmerView.isVisible = false
                binding.recyclerView.isVisible = true
            }
            is DialogStates.UpdatePlayedData -> {
                adapter.notifyItemChanged(state.position)
            }
            is DialogStates.Error -> {
                requireContext().showOneActionDialog(
                    "Error: ${state.message}", "Ok"
                ) {
                    it.dismiss()
                }
            }
        }
    }
}