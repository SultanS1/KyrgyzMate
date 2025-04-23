package alatoo.edu.kg.kyrgyzmate.data.dto.lessons

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.services.models.AudioItem
import java.io.File

data class Dialog(
    val dialogNumber: Int,
    val ky: String,
    val en: String,
    val audioFile: File,
    var isPlayed: Boolean = false
) : DelegateAdapterItem {
    override fun id(): Any {
        return dialogNumber
    }

    override fun content(): Any {
        return ky+en+isPlayed
    }
}

fun AudioItem.toDialog() : Dialog {
    return Dialog(
        dialogNumber = this.audioNumber,
        ky = this.ky,
        en = this.en,
        audioFile = this.audioFile
    )
}
