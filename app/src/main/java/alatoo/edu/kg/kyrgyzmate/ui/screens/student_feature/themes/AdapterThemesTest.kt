package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes

import alatoo.edu.kg.kyrgyzmate.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterThemesTest : RecyclerView.Adapter<AdapterThemesTest.ViewHolder>() {

    private val items = mutableListOf<String>("", "", "", "","", "","", "","", "","", "")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_theme, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView
    }
}