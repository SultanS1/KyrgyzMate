package alatoo.edu.kg.kyrgyzmate.data.dto.groups

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentGroupInfo
import android.os.Parcelable
import com.google.errorprone.annotations.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class GroupItem(
    val groupId: String = "",
    val groupName: String = "",
    val groupDescription: String = "",
    val createdBy: String = "",
    val creatorFullName: String = "",
    val members: List<StudentGroupInfo>? = emptyList()
) : DelegateAdapterItem, Parcelable {
    override fun id(): Any {
        return groupId
    }

    override fun content(): Any {
        return groupDescription
    }
}