package alatoo.edu.kg.kyrgyzmate.data.dto.lessons

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParentInfo(
    val id: String,
    val name: String
) : Parcelable