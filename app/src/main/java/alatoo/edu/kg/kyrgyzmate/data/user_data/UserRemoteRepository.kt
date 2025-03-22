package alatoo.edu.kg.kyrgyzmate.data.user_data

class UserRemoteRepository: UserRestRepository {
    override suspend fun getUserByEmail(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createUser() {
        TODO("Not yet implemented")
    }

    override suspend fun sendEmailDeeplink(email: String) {
        TODO("Not yet implemented")
    }
}