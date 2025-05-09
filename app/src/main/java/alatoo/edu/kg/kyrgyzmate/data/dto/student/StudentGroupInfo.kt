package alatoo.edu.kg.kyrgyzmate.data.dto.student

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.ZonedDateTime

@Parcelize
data class StudentGroupInfo(
    val groupId: String = "",
    val groupInfo: String = "",
    val fullName: String = "",
    val progress: String = "0%",
    val lastTopic: String = "Beginner",
    val lastSeen: ZonedDateTime? = null,
    val email: String = "",
    val passedItems: List<String> = listOf()
) : Parcelable, DelegateAdapterItem {
    override fun id(): Any {
        return fullName
    }

    override fun content(): Any {
        return fullName
    }
}