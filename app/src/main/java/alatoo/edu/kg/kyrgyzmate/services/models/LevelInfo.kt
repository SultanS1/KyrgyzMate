package alatoo.edu.kg.kyrgyzmate.services.models

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem

data class LevelInfo(
    val name: String,
    val folderId: String,
    val description: String,
    val lessons: String,
    val progress: Int
) : DelegateAdapterItem {
    override fun id(): Any {
        return name
    }

    override fun content(): Any {
        return name
    }

    fun getLevelColor(): Int {
        return when(name) {
            "Beginner" -> R.color.beginner_color
            "Elementary" -> R.color.elementary_color
            "Pre-Intermediate" -> R.color.pre_intermediate_color
            "Intermediate" -> R.color.intermediate_color
            "Upper-Intermediate" -> R.color.upper_intermediate_color
            "Advanced" -> R.color.advanced_color
            "Mastery" -> R.color.mastery_color
            else -> R.color.beginner_color
        }
    }
}
