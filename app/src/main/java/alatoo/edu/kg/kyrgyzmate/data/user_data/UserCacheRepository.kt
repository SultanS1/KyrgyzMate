package alatoo.edu.kg.kyrgyzmate.data.user_data

import android.content.SharedPreferences

private const val USER_IS_IN = "USER_IS_IN"
class UserCacheRepository(
    private val sharedPref: SharedPreferences
): UserLocalRepository {

    override fun recordUserLogin() {
        sharedPref.edit().putBoolean(USER_IS_IN, true).apply()
    }

    override fun checkIsUserLogin(): Boolean {
        return sharedPref.getBoolean(USER_IS_IN, false)
    }
}