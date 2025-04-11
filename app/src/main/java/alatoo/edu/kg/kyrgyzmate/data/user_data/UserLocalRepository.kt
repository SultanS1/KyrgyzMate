package alatoo.edu.kg.kyrgyzmate.data.user_data

import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData

interface UserLocalRepository {

    fun recordUserLogin(userRole: UserRole)
    fun isUserLoggedIn(): UserRole

    fun setRegistrationData(registrationData: UserRegistrationData? = null)
    fun getRegistrationData(): UserRegistrationData?

    fun setUserProfile(user: User)
    fun getUserProfile(): User?
}