package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentGroupInfo
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User

interface StudentRestRepository {

    suspend fun getStudentProfile(): FirebaseGetResponse<StudentProfile?>

    suspend fun getStudentGroupInfo(): FirebaseGetResponse<StudentGroupInfo?>

    suspend fun getGroupsList() : FirebaseGetResponse<List<GroupItem>>

    suspend fun updateProfile(
        name: String,
        surname: String,
        group: GroupItem?
    ) : FireBasePostResponse

    suspend fun updateStudentProgress(
        totalItemAmount: Int,
        itemsId: List<String>,
        lastPassedItem: String
    ): FireBasePostResponse
}