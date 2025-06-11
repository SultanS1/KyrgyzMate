package alatoo.edu.kg.kyrgyzmate.services

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.File

object AudioPlayer {
    private const val TAG = "DriveAudioPlayer"
    private var mediaPlayer: MediaPlayer? = null

    private var handler: Handler? = null
    private var updateRunnable: Runnable? = null

    fun playAudio(
        file: File,
        onPrepare: ((Int) -> Unit),
        onProgress: (currentPosition: Int) -> Unit,
        onComplete: (() -> Unit)
    ) {
        Log.d("AudioPlayer", "audioFile: $file")
        try {
            stopAudio()

            mediaPlayer = MediaPlayer()

            mediaPlayer?.apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(file.absolutePath)

                setOnPreparedListener {
                    start()
                    onPrepare.invoke(duration)
                    startProgressUpdates(onProgress)
                    Log.d(TAG, "Audio started")
                }

                setOnErrorListener { _, what, extra ->
                    Log.e(TAG, "Error occurred: what=$what, extra=$extra")
                    true
                }

                setOnCompletionListener {
                    Log.d(TAG, "Audio playback completed")
                    stopAudio()
                    onComplete.invoke()
                }

                prepareAsync()
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Error occurred: ${e.message}")
            stopAudio()
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
        handler?.removeCallbacks(updateRunnable ?: return)
        handler = null
        updateRunnable = null
    }

    private fun startProgressUpdates(onProgress: (Int) -> Unit) {
        handler = Handler(Looper.getMainLooper())
        updateRunnable = object : Runnable {
            override fun run() {
                mediaPlayer?.let {
                    onProgress(it.currentPosition)
                    handler?.postDelayed(this, 10)
                }
            }
        }
        handler?.post(updateRunnable!!)
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
    }
}
