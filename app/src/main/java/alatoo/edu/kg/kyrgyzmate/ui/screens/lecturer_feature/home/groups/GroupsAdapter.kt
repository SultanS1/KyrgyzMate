package alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.groups

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapter
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.databinding.ItemGroupBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GroupsAdapter(
    private val onClick: (GroupItem) -> Unit
) : DelegateAdapter<GroupItem, GroupsAdapter.ViewHolder>(GroupItem::class.java) {

    inner class ViewHolder(
        private val binding: ItemGroupBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GroupItem) {
            with(binding) {
                root.pressCompressInAnimation()
                root.setClickListener {
                    onClick(item)
                }
                groupName.text = item.groupName
                groupDescription.text = item.groupDescription
                groupMembersAmountTextView.text = "${item.members?.size} members"
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: GroupItem,
        viewHolder: GroupsAdapter.ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }
}