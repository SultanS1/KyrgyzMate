package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.words

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapter
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Word
import alatoo.edu.kg.kyrgyzmate.databinding.ItemWordBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.services.AudioPlayer
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WordsAdapter(
    private val onPlayed: (Int) -> Unit
) : DelegateAdapter<Word, WordsAdapter.ViewHolder>(Word::class.java) {

    inner class ViewHolder(
        private val binding: ItemWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Word) {
            with(binding) {
                originalTextView.text = item.ky
                translationTextView.text = item.en
                root.pressCompressInAnimation()
                if (item.isPlayed) root.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                root.setClickListener {
                    AudioPlayer.playAudio(
                        file = item.audioFile,
                        onPrepare = {},
                        onProgress = {},
                        onComplete = {
                            item.isPlayed = true
                            onPlayed(adapterPosition)
                        }
                    )
                }
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: Word,
        viewHolder: WordsAdapter.ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }
}