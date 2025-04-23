package alatoo.edu.kg.kyrgyzmate.data.dto.student

import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole

data class StudentProfile(
    val name: String? = null,
    val surname: String? = null,
    val group: String? = null,
    val role: UserRole? = null,
    val email: String? = null
) {

    fun getFullName(): String {
        return "$name $surname"
    }
}

