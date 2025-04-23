package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapter
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.databinding.ItemTopicBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TopicsAdapter(
    private val onClick: (DriveItem) -> Unit
) : DelegateAdapter<DriveItem, TopicsAdapter.ViewHolder>(DriveItem::class.java) {

    inner class ViewHolder(
        private val binding: ItemTopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DriveItem) {
            with(binding) {
                titleTextView.text = item.name
                root.setClickListener { onClick(item) }
                root.pressCompressInAnimation()
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: DriveItem,
        viewHolder: TopicsAdapter.ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }
}