package alatoo.edu.kg.kyrgyzmate.data.user_data

import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRole
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow

private const val USER_ROLE = "USER_ROLE"
class UserCacheRepository(
    private val sharedPref: SharedPreferences
): UserLocalRepository {

    private val userDataFlow: MutableStateFlow<UserRegistrationData?> = MutableStateFlow(null)

    private val userProfileFlow:  MutableStateFlow<User?> = MutableStateFlow(null)

    override fun setUserRole(userRole: UserRole) {
        sharedPref.edit().putString(USER_ROLE, userRole.name).apply()
    }

    override fun getUserRole(): UserRole {
        return try {
            val result = sharedPref.getString(USER_ROLE, UserRole.UNKOWN.name)
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