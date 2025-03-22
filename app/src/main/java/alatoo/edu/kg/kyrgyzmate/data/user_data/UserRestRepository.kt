package alatoo.edu.kg.kyrgyzmate.data.user_data

interface UserRestRepository {

    suspend fun getUserByEmail(email: String)

    suspend fun createUser()

    suspend fun sendEmailDeeplink(email: String)
}