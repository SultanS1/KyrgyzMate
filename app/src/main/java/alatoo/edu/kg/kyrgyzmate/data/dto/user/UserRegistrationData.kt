package alatoo.edu.kg.kyrgyzmate.data.dto.user

data class UserRegistrationData(
    val firstName: String? = null,
    val lastName: String? = null,
    val group: String? = null,
    val role: UserRole? = null,
    val email: String? = null
)
