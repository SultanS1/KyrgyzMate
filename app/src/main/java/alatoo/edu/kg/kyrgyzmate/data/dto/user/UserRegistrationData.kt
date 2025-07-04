package alatoo.edu.kg.kyrgyzmate.data.dto.user

data class UserRegistrationData(
    val firstName: String? = null,
    val lastName: String? = null,
    val group: String = "NONE",
    val role: UserRole? = null,
    val email: String? = null
)
