package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.texts

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.core.BaseFragment
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentTextsBinding
import alatoo.edu.kg.kyrgyzmate.extensions.formatMillisToTime
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import alatoo.edu.kg.kyrgyzmate.services.AudioPlayer
import alatoo.edu.kg.kyrgyzmate.services.DocsReader
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics.TopicsFragmentArgs
import android.os.Bundle
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TextsFragment
    : BaseFragment<FragmentTextsBinding, TextStates, TextsActions>(R.layout.fragment_texts) {

    override val binding by viewBinding(FragmentTextsBinding::bind)

    override val viewModel by viewModel<TextsViewModel>()

    override fun setupUI() {
        val args: TextsFragmentArgs by navArgs()
        args.id?.let { viewModel.submitAction(TextsActions.LoadData(it)) }

        binding.arrowBackButton.setClickListener {
            val bundle = Bundle().apply {
                putString("topic_id", "")
            }
            parentFragmentManager.setFragmentResult("my_result_key", bundle)

            findNavController().popBackStack() }
    }

    override fun renderState(state: TextStates) {
        with(binding) {
            when(state) {
                is TextStates.Error -> showErrorDialog(state.messageRes)
                TextStates.Loading -> {
                    shimmerView.isVisible = true
                    audioControlContainer.isVisible = false
                    textTextView.isVisible = false
                }
                is TextStates.ShowText -> {
                    textTextView.text = state.text
                    audioControlButton.setClickListener {
                        AudioPlayer.playAudio(
                            file = state.audio,
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
                    shimmerView.isVisible = false
                    audioControlContainer.isVisible = true
                    textTextView.isVisible = true
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        AudioPlayer.stopAudio()
    }
}