package alatoo.edu.kg.kyrgyzmate.data.lecturer_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentGroupInfo
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class LecturerRemoteRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: DatabaseReference
) : LecturerRestRepository {

    override suspend fun createGroup(
        user: User,
        groupName: String,
        groupDescription: String
    ): FireBasePostResponse {
        return try {

            val groupRef = firebaseDb.child("groups").push()
            val groupId = groupRef.key ?: throw IllegalStateException("Failed to generate group ID")

            val group = GroupItem(
                groupId = groupId,
                groupName = groupName,
                groupDescription = groupDescription,
                createdBy = user.uid,
                creatorFullName = "${user.name} ${user.surname}",
                )
            firebaseDb.child("groups").child(groupId).setValue(group).await()
            FireBasePostResponse.SUCCESS

        } catch (e: Exception) {
            Log.e("LecturerRemoteRepo", "Error creating group: ${e.message}")
            FireBasePostResponse.UNKNOWN_ERROR
        }
    }

    override suspend fun getTeachersGroups(
        user: User
    ): FirebaseGetResponse<List<GroupItem>> {
        return try {
            val snapshot = firebaseDb.child("groups")
                .orderByChild("createdBy")
                .equalTo(user.uid)
                .get()
                .await()

            val groups = mutableListOf<GroupItem>()

            if(!snapshot.exists()) {
                return FirebaseGetResponse.Success(groups)
            }

            val studentInfoSnapshot = firebaseDb.child("student_info")
                .get()
                .await()

            for (child in snapshot.children) {
                val group = child.getValue(GroupItem::class.java)
                val groupId = group?.groupId ?: continue

                val memberList = mutableListOf<StudentGroupInfo>()
                for (studentChild in studentInfoSnapshot.children) {
                    val info = studentChild.getValue(StudentGroupInfo::class.java)
                    if (info?.groupId == groupId) {
                        memberList.add(info)
                    }
                }
                val groupWithMembers = group.copy(members = memberList)
                groups.add(groupWithMembers)
            }
            FirebaseGetResponse.Success(groups)
        } catch (t: Throwable) {
            Log.e("LecturerRemoteRepo", "Error fetching groups: ${t.stackTraceToString()}")
            FirebaseGetResponse.Error(t.message.toString())
        }
    }
}