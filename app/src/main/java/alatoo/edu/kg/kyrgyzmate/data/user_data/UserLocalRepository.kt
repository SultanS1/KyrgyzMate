package alatoo.edu.kg.kyrgyzmate.data.user_data

interface UserLocalRepository {

    fun recordUserLogin()
    fun checkIsUserLogin(): Boolean

}