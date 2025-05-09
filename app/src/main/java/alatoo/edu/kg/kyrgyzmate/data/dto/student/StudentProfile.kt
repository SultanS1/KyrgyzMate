package alatoo.edu.kg.kyrgyzmate.data.dto.student

import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StudentProfile(
    val name: String? = null,
    val surname: String? = null,
    val group: String? = null,
    val role: UserRole? = null,
    val email: String? = null,
    val studentGroupInfo: StudentGroupInfo? = null
) : Parcelable {

    fun getFullName(): String {
        return "$name $surname"
    }
}

