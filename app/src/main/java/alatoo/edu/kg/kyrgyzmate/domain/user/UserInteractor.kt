package alatoo.edu.kg.kyrgyzmate.domain.user

import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserRestRepository
import alatoo.edu.kg.kyrgyzmate.modules.APP_PREFS
import javax.inject.Named

class UserInteractor(
    private val userLocalRepository: UserLocalRepository,
    @Named(APP_PREFS)
    private val userRestRepository: UserRestRepository
) {
    // Network
    suspend fun registerUser(email: String): FireBasePostResponse {
        return try {
            val result = userRestRepository.registerUser(email)

            if (result == FireBasePostResponse.USER_EXISTS) {
                val verificationStatus = userRestRepository.checkEmailVerificationStatus()

                if (verificationStatus == FireBasePostResponse.EMAIL_NOT_VERIFIED) {
                    val deleteStatus = userRestRepository.deleteUser()
                    if (deleteStatus == FireBasePostResponse.SUCCESS) {
                        val newRegistrationResult = userRestRepository.registerUser(email)
                        if (newRegistrationResult == FireBasePostResponse.SUCCESS) {
                            return userRestRepository.sendEmailVerification()
                        }
                        return newRegistrationResult
                    }
                }
            } else if (result == FireBasePostResponse.SUCCESS) {
                return userRestRepository.sendEmailVerification()
            }
            result
        } catch (e: Exception) {
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    suspend fun checkEmailVerificationStatus(): FireBasePostResponse {
        return userRestRepository.checkEmailVerificationStatus()
    }

    suspend fun createPassword(password: String): FireBasePostResponse {
        val createPassword = userRestRepository.createPassword(password)
        if (createPassword != FireBasePostResponse.SUCCESS) {
            return createPassword
        }
        return userRestRepository.createUserProfile(userLocalRepository.getRegistrationData())
    }

    suspend fun loginUser(email: String, password: String): FireBasePostResponse {
        val result = userRestRepository.loginUser(email, password)
        if (result != FireBasePostResponse.SUCCESS) {
            return result
        }
        val user = userRestRepository.getUserProfile()
        if (user is FirebaseGetResponse.Success) {
            setUserRole(UserRole.valueOf(user.data.role))
            userLocalRepository.setUserProfile(user.data)
        }
        return result
    }

    suspend fun signOut(): FireBasePostResponse {
        return userRestRepository.signOut()
    }

    suspend fun resetPassword(email: String): FireBasePostResponse {
        return userRestRepository.resetPassword(email)
    }

    suspend fun userSession(): Boolean {
        val user = userRestRepository.getUserProfile()
        if (user is FirebaseGetResponse.Success) {
            userLocalRepository.setUserProfile(user.data)
        }
        return userRestRepository.userSessionIsActive()
    }

    // Cache
    private fun setUserRole(userRole: UserRole) {
        userLocalRepository.setUserRole(userRole)
    }

    fun getUserRole(): UserRole {
        return userLocalRepository.getUserRole()
    }

    fun setRegistrationData(userRegistrationData: UserRegistrationData?) {
        userLocalRepository.setRegistrationData(userRegistrationData)
    }

}