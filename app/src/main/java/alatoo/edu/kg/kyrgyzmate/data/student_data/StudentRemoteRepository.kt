package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentGroupInfo
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.utils.Utils
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

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

    override suspend fun getStudentGroupInfo(): FirebaseGetResponse<StudentGroupInfo?> {
        return try {
            val uid = firebaseAuth.currentUser
            Log.d("StudentRemoteRepository", uid.toString())
            FirebaseGetResponse.Success(firebaseDb.child("student_info").child(uid?.uid!!).get().await().getValue(StudentGroupInfo::class.java))
        } catch (t: Throwable) {
            Log.e("StudentRemoteRepository", t.localizedMessage)
            FirebaseGetResponse.Error(t.message.toString())
        }
    }

    override suspend fun getGroupsList(): FirebaseGetResponse<List<GroupItem>> {
        return try {
            val groupsSnapshot = firebaseDb.child("groups")
                .get()
                .await()

            val groups = mutableListOf<GroupItem>()

            for (groupSnap in groupsSnapshot.children) {
                val group = groupSnap.getValue(GroupItem::class.java)
                group?.let { groups.add(it) }
            }
            return FirebaseGetResponse.Success(groups)
        } catch (t: Throwable) {
            Log.e("StudentRemoteRepository", t.localizedMessage)
            FirebaseGetResponse.Error(t.message.toString())
        }
    }

    override suspend fun updateProfile(
        name: String,
        surname: String,
        group: GroupItem?
    ): FireBasePostResponse {
        val uid = firebaseAuth.currentUser?.uid
            ?: return FireBasePostResponse.PERMISSION_DENIED

        val profileUpdates = mutableMapOf<String, Any?>(
            "name" to name,
            "surname" to surname
        )

        val groupInfoUpdates = mutableMapOf<String, Any?>(
            "groupInfo" to "${group?.groupName} / ${group?.creatorFullName}",
            "fullName" to "$name $surname"
        )

        if(group != null) groupInfoUpdates["groupId"] = group.groupId

        return try {
            val userRef = firebaseDb.child("users/$uid")
            val groupInfoRef = firebaseDb.child("student_info/$uid")
            userRef.updateChildren(profileUpdates).await()
            groupInfoRef.updateChildren(groupInfoUpdates).await()

            FireBasePostResponse.SUCCESS
        } catch (t: Throwable) {
            Log.e("StudentRemoteRepository", t.message.toString())
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun updateStudentProgress(
        totalItemAmount: Int,
        itemsId: List<String>,
        lastPassedItem: String
    ): FireBasePostResponse {

        val uid = firebaseAuth.currentUser?.uid
            ?: return FireBasePostResponse.PERMISSION_DENIED

        val groupInfoUpdates = mutableMapOf<String, Any?>(
            "lastTopic" to lastPassedItem,
            "passedItems" to itemsId,
            "progress" to "5%",
            "lastSeenAt" to LocalDateTime.now().toString()
        )

        return try {
            val groupInfoRef = firebaseDb.child("student_info/$uid")
            groupInfoRef.updateChildren(groupInfoUpdates).await()

            FireBasePostResponse.SUCCESS
        } catch (t: Throwable) {
            Log.e("StudentRemoteRepository", t.message.toString())
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }
}