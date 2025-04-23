package alatoo.edu.kg.kyrgyzmate.services.models

import java.io.File

data class AudioItem(
    val audioNumber: Int,
    val ky: String,
    val en: String,
    val audioFile: File
)
