package alatoo.edu.kg.kyrgyzmate.data.dto.lessons

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.services.models.AudioItem
import java.io.File

data class Word(
    val wordNumber: Int,
    val ky: String,
    val en: String,
    val audioFile: File,
    var isPlayed: Boolean = false
) : DelegateAdapterItem {
    override fun id(): Any {
        return wordNumber
    }

    override fun content(): Any {
        return ky+en+isPlayed
    }
}

fun AudioItem.toWord(): Word {
    return Word(
        wordNumber = this.audioNumber,
        ky = this.ky,
        en = this.en,
        audioFile = this.audioFile
    )
}