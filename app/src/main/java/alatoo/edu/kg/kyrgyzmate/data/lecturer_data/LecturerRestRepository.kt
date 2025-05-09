package alatoo.edu.kg.kyrgyzmate.data.lecturer_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User

interface LecturerRestRepository {

    suspend fun createGroup(
        user: User,
        groupName: String,
        groupDescription: String
    ): FireBasePostResponse

    suspend fun getTeachersGroups(
        user: User
    ): FirebaseGetResponse<List<GroupItem>>
}