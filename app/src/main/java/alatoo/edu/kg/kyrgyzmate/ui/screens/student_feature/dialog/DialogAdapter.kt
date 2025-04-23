package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.dialog

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapter
import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.Dialog
import alatoo.edu.kg.kyrgyzmate.databinding.ItemSentenceBinding
import alatoo.edu.kg.kyrgyzmate.extensions.formatMillisToTime
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.services.AudioPlayer
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DialogAdapter(
    private val onPlayed: (Int) -> Unit
) : DelegateAdapter<Dialog, DialogAdapter.ViewHolder>(Dialog::class.java) {

    inner class ViewHolder(
        private val binding: ItemSentenceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Dialog) {
            with(binding) {
                originalTextView.text = item.ky
                translationTextView.text = item.en
                audioControlButton.pressCompressInAnimation()
                if (item.isPlayed) root.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                audioControlButton.setClickListener {
                    AudioPlayer.playAudio(
                        file = item.audioFile,
                        onPrepare = { duration ->
                            audioControlButton.setImageResource(R.drawable.ic_stop_audio_blue)
                            timerText.text = duration.formatMillisToTime()
                            seekBar.max = duration
                        },
                        onProgress = { position ->
                            binding.seekBar.progress = position
                            binding.timerText.text = position.formatMillisToTime()
                        },
                        onComplete = {
                            audioControlButton.setImageResource(R.drawable.ic_play_audio_blue)
                            seekBar.progress = 0
                            item.isPlayed = true
                            onPlayed.invoke(adapterPosition)
                        }
                    )
                }

                seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            AudioPlayer.seekTo(position = progress)
                        }
                    }
                    override fun onStartTrackingTouch(sb: SeekBar?) {}
                    override fun onStopTrackingTouch(sb: SeekBar?) {}
                })
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemSentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: Dialog,
        viewHolder: DialogAdapter.ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }
}