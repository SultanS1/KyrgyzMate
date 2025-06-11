package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.group

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapter
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentGroupInfo
import alatoo.edu.kg.kyrgyzmate.databinding.ItemStudentsBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.utils.Utils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StudentsAdapter
    : DelegateAdapter<StudentGroupInfo, StudentsAdapter.ViewHolder>(StudentGroupInfo::class.java) {

    inner class ViewHolder(
        private val binding: ItemStudentsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StudentGroupInfo) {
            with(binding) {
                root.pressCompressInAnimation()
                fullNameTextView.text = item.fullName
                progressTextView.text = item.progress
                lastSeenTextView.text = if(item.lastSeenAt == null) "long time ago" else Utils.formatLastSeen(LocalDateTime.parse(item.lastSeenAt, DateTimeFormatter.ISO_DATE_TIME))
                lastTopicTextView.text = item.lastTopic
                emailTextView.text = item.email
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemStudentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: StudentGroupInfo,
        viewHolder: StudentsAdapter.ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }
}