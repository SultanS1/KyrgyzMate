package alatoo.edu.kg.kyrgyzmate.data.user_data

import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow

private const val USER_IS_IN = "USER_IS_IN"
class UserCacheRepository(
    private val sharedPref: SharedPreferences
): UserLocalRepository {

    private val userDataFlow: MutableStateFlow<UserRegistrationData?> = MutableStateFlow(null)

    private val userProfileFlow:  MutableStateFlow<User?> = MutableStateFlow(null)

    override fun recordUserLogin(userRole: UserRole) {
        sharedPref.edit().putString(USER_IS_IN, userRole.name).apply()
    }

    override fun isUserLoggedIn(): UserRole {
        return try {
            val result = sharedPref.getString(USER_IS_IN, UserRole.UNKOWN.name)
            UserRole.valueOf(result!!)
        } catch (e: Exception) {
            UserRole.UNKOWN
        }
    }

    override fun setRegistrationData(userRegistrationData: UserRegistrationData?) {
        userDataFlow.value = userRegistrationData
    }

    override fun getRegistrationData(): UserRegistrationData? {
        return userDataFlow.value
    }

    override fun setUserProfile(user: User) {
        userProfileFlow.value = user
    }

    override fun getUserProfile(): User? {
        return userProfileFlow.value
    }
}