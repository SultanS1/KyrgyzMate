package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapter
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.databinding.ItemCourseBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.services.models.LevelInfo
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class LevelsAdapter(
    private val onClick: (LevelInfo) -> Unit
) : DelegateAdapter<LevelInfo, LevelsAdapter.ViewHolder>(LevelInfo::class.java) {

    inner class ViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(levelInfo: LevelInfo) {
            with(binding) {
                root.pressCompressInAnimation()
                levelTextView.text = levelInfo.name
                levelDescriptionTextView.text = levelInfo.description
                levelLessons.text = levelInfo.lessons
                levelProgress.text = levelInfo.progress.toString() + "%"
                progressBar1.progress = levelInfo.progress

                val tintColor = ContextCompat.getColor(root.context, levelInfo.getLevelColor())
                root.backgroundTintList = ColorStateList.valueOf(tintColor)
                root.setOnClickListener {
                    onClick.invoke(levelInfo)
                }
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: LevelInfo,
        viewHolder: ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }
}