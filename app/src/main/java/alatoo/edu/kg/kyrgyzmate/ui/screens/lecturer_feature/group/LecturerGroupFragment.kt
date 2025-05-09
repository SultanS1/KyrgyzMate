package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.group

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.adapter.MainCompositeAdapter
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentLecturerGroupDetailsBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding

class LecturerGroupFragment: Fragment(R.layout.fragment_lecturer_group_details) {

    private val binding by viewBinding(FragmentLecturerGroupDetailsBinding::bind)

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(StudentsAdapter())
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: LecturerGroupFragmentArgs by navArgs()
        val group = args.group
        with(binding) {
            adapter.submitList(group?.members)
            membersRecyclerView.adapter = adapter
            groupNameTextView.text = group?.groupName

            val hasMembers = !group?.members.isNullOrEmpty()

            membersRecyclerView.isVisible = hasMembers
            shimmerView.isVisible = false
            emptyStatusTextView.isVisible = !hasMembers

            arrowBackButton.pressCompressInAnimation()
            arrowBackButton.setClickListener { findNavController().navigateUp() }
        }
    }
}