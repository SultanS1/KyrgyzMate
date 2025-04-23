package alatoo.edu.kg.kyrgyzmate.data.user_data

import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData

interface UserRestRepository {

    suspend fun registerUser(email: String): FireBasePostResponse

    suspend fun sendEmailVerification(): FireBasePostResponse

    suspend fun checkEmailVerificationStatus(): FireBasePostResponse

    suspend fun createPassword(password: String): FireBasePostResponse

    suspend fun userSessionIsActive(): Boolean

    suspend fun loginUser(email: String, password: String): FireBasePostResponse

    suspend fun signOut(): FireBasePostResponse

    suspend fun deleteUser(): FireBasePostResponse

    suspend fun resetPassword(email: String): FireBasePostResponse

    suspend fun createUserProfile(userRegistrationData: UserRegistrationData?): FireBasePostResponse

    suspend fun getUserProfile(): FirebaseGetResponse<User>
}
