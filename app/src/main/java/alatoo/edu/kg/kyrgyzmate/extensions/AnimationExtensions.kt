package alatoo.edu.kg.kyrgyzmate.extensions

import alatoo.edu.kg.kyrgyzmate.R
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

@SuppressLint("ClickableViewAccessibility")
fun View.pressHorizontalStretchAnimation() {
    this.setOnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                view.animate()
                    .scaleX(1.02f)
                    .scaleY(0.98f)
                    .setDuration(50)
                    .start()
                true
            }
            MotionEvent.ACTION_UP -> {
                view.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(50)
                    .withEndAction {
                        view.performClick()
                    }
                    .start()
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                view.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(50)
                    .start()
                true
            }
            else -> false
        }
    }
}

@SuppressLint("ClickableViewAccessibility")
fun View.pressCompressInAnimation() {
    this.setOnTouchListener { view, event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                view.animate()
                    .scaleX(0.98f)
                    .scaleY(0.98f)
                    .setDuration(50)
                    .start()
                true
            }
            MotionEvent.ACTION_UP -> {
                view.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(50)
                    .withEndAction {
                        view.performClick()
                    }
                    .start()
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                view.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(50)
                    .start()
                true
            }
            else -> false
        }
    }
}

