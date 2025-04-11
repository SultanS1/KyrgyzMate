package alatoo.edu.kg.kyrgyzmate.services

import alatoo.edu.kg.kyrgyzmate.core.BaseUrls
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log

object AudioPlayer {
    private const val TAG = "DriveAudioPlayer"
    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(context: Context, fileId: String, onComplete: (() -> Unit)? = null) {
        stopAudio() // Stop any previously playing audio

        mediaPlayer = MediaPlayer()
        val audioUrl = "https://www.googleapis.com/drive/v3/files/$fileId?alt=media&key=${BaseUrls.API_KEY}"

        mediaPlayer?.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(context, Uri.parse(audioUrl))

            setOnPreparedListener {
                it.start()
                Log.d(TAG, "Audio started")
            }

            setOnErrorListener { _, what, extra ->
                Log.e(TAG, "Error occurred: what=$what, extra=$extra")
                true
            }

            setOnCompletionListener {
                Log.d(TAG, "Audio playback completed")
                stopAudio()
                onComplete?.invoke()
            }

            prepareAsync()
        }
    }

    fun stopAudio() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
                Log.d(TAG, "Audio stopped")
            }
            release()
        }
        mediaPlayer = null
    }
}
