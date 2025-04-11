package alatoo.edu.kg.kyrgyzmate.extensions

import android.view.View

private const val DEFAULT_CLICK_DELAY = 400L

fun View.setClickListener(delay: Long = DEFAULT_CLICK_DELAY, onClick: (View) -> Unit) {
    var lastClickTime = 0L

    this.setOnClickListener { view ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= delay) {
            lastClickTime = currentTime
            onClick(view)
        }
    }
}