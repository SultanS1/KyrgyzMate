package alatoo.edu.kg.kyrgyzmate.extensions


fun Int.toPercentage(): String {
    return "${this}%"
}

fun Int.toProgressType(): Float {
    return this.toFloat()/100f
}