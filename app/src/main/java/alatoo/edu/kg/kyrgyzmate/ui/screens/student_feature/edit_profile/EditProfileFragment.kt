package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.edit_profile

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentEditProfileBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.extensions.showLoadingDialog
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes.ThemesFragmentArgs
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment
    : BaseFragment<FragmentStudentEditProfileBinding, EditProfileStates, EditProfileActions>(R.layout.fragment_student_edit_profile) {

    override val binding by viewBinding(FragmentStudentEditProfileBinding::bind)

    override val viewModel by viewModel<EditProfileViewModel>()

    private var selectedGroup: GroupItem? = null

    override fun setupUI() {
        val args: EditProfileFragmentArgs by navArgs()
        val profile = args.studentProfile

        with(binding) {
            firstNameEditText.setText(profile?.name ?: "Name")
            lastNameEditText.setText(profile?.surname ?: "Surname")
            groupEditText.setText(profile?.studentGroupInfo?.groupInfo ?: "Group")

            viewModel.submitAction(EditProfileActions.LoadData)
            saveChangesButton.pressCompressInAnimation()
            cancelButton.pressCompressInAnimation()

            cancelButton.setClickListener {
                viewModel.submitAction(EditProfileActions.Cancel)
            }
            saveChangesButton.setClickListener {
                viewModel.submitAction(EditProfileActions.SubmitData(
                    firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(),
                    selectedGroup
                ))
            }
        }
    }

    override fun renderState(state: EditProfileStates) {
        when(state) {
            is EditProfileStates.Error -> showErrorDialog(state.messageRes)
            is EditProfileStates.Loading -> {
                requireContext().showLoadingDialog(viewLifecycleOwner.lifecycle)
            }
            is EditProfileStates.ProfileFields -> {
                with(binding) {
                    firstNameContainer.error = state.firstNameState
                    firstNameContainer.isErrorEnabled = state.firstNameState != null

                    lastNameContainer.error = state.lastNameState
                    lastNameContainer.isErrorEnabled = state.lastNameState != null
                }
            }
            is EditProfileStates.ShowProfileData -> setGroupList(state.groupOptions)

            EditProfileStates.NavigateBack -> findNavController().navigateUp()
        }
    }

    private fun setGroupList(groups: List<GroupItem>) {
        with(binding) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                groups.map {"${it.groupName} / ${it.creatorFullName}"}
            )
            groupEditText.setAdapter(adapter)

            groupEditText.setOnClickListener {
                groupEditText.showDropDown()
            }
            groupEditText.setOnItemClickListener { _, _, position, _ ->
                selectedGroup = groups[position]
            }
        }
    }
}