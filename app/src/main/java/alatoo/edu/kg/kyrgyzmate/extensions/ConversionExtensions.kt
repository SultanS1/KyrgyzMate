package alatoo.edu.kg.kyrgyzmate.extensions


fun Int.toPercentage(): String {
    return "${this}%"
}

fun Int.toProgressType(): Float {
    return this.toFloat()/100f
}

fun Int.formatMillisToTime(): String {
    val totalSeconds = this / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}