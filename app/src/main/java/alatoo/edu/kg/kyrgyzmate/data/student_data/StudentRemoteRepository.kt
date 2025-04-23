package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class StudentRemoteRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: DatabaseReference
) : StudentRestRepository {

    override suspend fun getStudentProfile(): FirebaseGetResponse<StudentProfile?> {
        return try {
            val uid = firebaseAuth.currentUser
            Log.d("StudentRemoteRepository", uid.toString())
            FirebaseGetResponse.Success(firebaseDb.child("users").child(uid?.uid!!).get().await().getValue(StudentProfile::class.java))
        } catch (t: Throwable) {
            Log.e("StudentRemoteRepository", t.localizedMessage)
            FirebaseGetResponse.Error(t.message.toString())
        }
    }

}