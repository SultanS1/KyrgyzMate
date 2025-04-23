package alatoo.edu.kg.kyrgyzmate.data.dto.lessons

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThemeType(
    val name: String,
    val id: String
) : Parcelable