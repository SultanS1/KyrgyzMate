package alatoo.edu.kg.kyrgyzmate.data.user_data

import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.data.dto.user.UserRegistrationData
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class UserRemoteRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: DatabaseReference
): UserRestRepository {

    override suspend fun registerUser(email: String): FireBasePostResponse {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, "password").await()
            if(authResult.user != null) {
                FireBasePostResponse.SUCCESS
            } else {
                FireBasePostResponse.FAILED
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Log.e("UserRepository", "${e.message}")
            FireBasePostResponse.INVALID_EMAIL
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.e("UserRepository", "${e.message}")
            FireBasePostResponse.USER_EXISTS
        } catch (e: Exception) {
            Log.e("UserRepository", "${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun sendEmailVerification(): FireBasePostResponse {
        return try {
            val user = firebaseAuth.currentUser ?: return FireBasePostResponse.USER_NOT_FOUND
            user.sendEmailVerification().await()
            Log.e("UserRepository", "link is sent")
            FireBasePostResponse.SUCCESS
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun checkEmailVerificationStatus(): FireBasePostResponse {
        return try {
            val user = firebaseAuth.currentUser
            user?.reload()?.await()
            if (user?.isEmailVerified == true) {
                Log.d("UserRepository", "Success")
                FireBasePostResponse.SUCCESS
            } else {
                Log.d("UserRepository", "Not verified")
                FireBasePostResponse.EMAIL_NOT_VERIFIED
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun createPassword(password: String): FireBasePostResponse {
        return try {
            firebaseAuth.currentUser?.updatePassword(password)?.await()
            Log.d("UserRepository", "Password is created")
            FireBasePostResponse.SUCCESS
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun userSessionIsActive(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun loginUser(email: String, password: String): FireBasePostResponse {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            FireBasePostResponse.SUCCESS
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Log.e("UserRepository", "Failed: ${e.message}")
            FireBasePostResponse.WRONG_CREDENTIALS
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun signOut(): FireBasePostResponse {
        return try {
            firebaseAuth.signOut()
            FireBasePostResponse.SUCCESS
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun deleteUser(): FireBasePostResponse {
        val user = firebaseAuth.currentUser ?: return FireBasePostResponse.USER_NOT_FOUND

        return try {
            user.delete().await()
            firebaseAuth.signOut()
            Log.d("UserRepository", "Success")
            FireBasePostResponse.SUCCESS
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            Log.e("UserRepository", "Failed: ${e.message}")
            FireBasePostResponse.FAILED
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun resetPassword(email: String): FireBasePostResponse {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            FireBasePostResponse.SUCCESS
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun createUserProfile(userRegistrationData: UserRegistrationData?): FireBasePostResponse {
        val user = User(
            uid = firebaseAuth.currentUser?.uid ?: "",
            name = userRegistrationData?.firstName ?: "",
            surname = userRegistrationData?.lastName ?: "",
            email = userRegistrationData?.email ?: "",
            role = userRegistrationData?.role?.name ?: "STUDENT"
        )
        return try {
            firebaseDb.child("users").child(user.uid).setValue(user).await()
            FireBasePostResponse.SUCCESS
        } catch (e: Exception) {
            Log.e("UserRepository", "Error in profile creation: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun getUserProfile(): FirebaseGetResponse<User> {
        return try {
            val uid = firebaseAuth.currentUser?.uid ?: ""
            val snapshot = firebaseDb.child("users").child(uid).get().await()
            if (snapshot.exists()) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    FirebaseGetResponse.Success(user)
                } else {
                    FirebaseGetResponse.Error("User data is null")
                }
            } else {
                Log.e("UserRepository", "not found")
                FirebaseGetResponse.Error("Not found")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error: ${e.message}")
            FirebaseGetResponse.Error(e.message.toString())
        }
    }
}
