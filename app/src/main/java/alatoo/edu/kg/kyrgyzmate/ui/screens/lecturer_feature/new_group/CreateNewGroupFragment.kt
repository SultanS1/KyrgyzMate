package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.new_group

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentCreateNewGroupBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showLoadingDialog
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNewGroupFragment
    : BaseFragment<FragmentCreateNewGroupBinding, CreateNewGroupStates, CreateNewGroupActions>(R.layout.fragment_create_new_group) {

    override val binding by viewBinding(FragmentCreateNewGroupBinding::bind)

    override val viewModel by viewModel<CreateNewGroupViewModel>()

    override fun setupUI() {
        with(binding) {
            arrowBackButton.pressCompressInAnimation()
            arrowBackButton.setClickListener {
                viewModel.submitAction(CreateNewGroupActions.Popup)
            }
            createGroupButton.setClickListener {
                viewModel.submitAction(
                    CreateNewGroupActions.SubmitData(
                        groupNameEditText.text.toString(),
                        groupDescriptionEditText.text.toString()
                    )
                )
            }
        }
    }

    override fun renderState(state: CreateNewGroupStates) {
        when(state) {
            is CreateNewGroupStates.Error -> showErrorDialog(R.string.error_unknown_error)
            is CreateNewGroupStates.GroupFieldsState -> {
                binding.groupNameContainer.error = state.nameState
                binding.groupNameContainer.isErrorEnabled = state.nameState != null

                binding.groupDescriptionContainer.error = state.descriptionState
                binding.groupDescriptionContainer.isErrorEnabled = state.descriptionState != null
            }
            is CreateNewGroupStates.Loading -> requireContext().showLoadingDialog(viewLifecycleOwner.lifecycle)
            is CreateNewGroupStates.Popup -> findNavController().navigateUp()
            is CreateNewGroupStates.Success -> findNavController().navigateUp()
        }
    }
}