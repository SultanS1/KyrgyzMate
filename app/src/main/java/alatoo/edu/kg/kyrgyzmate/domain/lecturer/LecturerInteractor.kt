package alatoo.edu.kg.kyrgyzmate.domain.lecturer

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import alatoo.edu.kg.kyrgyzmate.data.lecturer_data.LecturerLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.lecturer_data.LecturerRestRepository
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserRestRepository
import android.util.Log

class LecturerInteractor(
    private val lecturerRestRepository: LecturerRestRepository,
    private val lecturerLocalRepository: LecturerLocalRepository,
    private val userLocalRepository: UserLocalRepository,
    private val userRestRepository: UserRestRepository
) {

    suspend fun createGroup(groupName: String, description: String): FireBasePostResponse {
        return lecturerRestRepository.createGroup(userLocalRepository.getUserProfile()!!, groupName, description)
    }

    suspend fun getLecturerGroups(): FirebaseGetResponse<List<GroupItem>> {
        val cache = lecturerLocalRepository.getGroups()
        if (cache == null) {
            val result = lecturerRestRepository.getTeachersGroups(userLocalRepository.getUserProfile()!!)
            if (result is FirebaseGetResponse.Success) {
                lecturerLocalRepository.setGroups(result.data)
            }
            Log.d("LecturerInteractor", result.toString())
            return result
        }
        return FirebaseGetResponse.Success(cache)
    }

    suspend fun getUserProfile(refresh: Boolean = false): FirebaseGetResponse<User?> {
        val cache = userLocalRepository.getUserProfile()
        if(cache == null || refresh) {
            val user = userRestRepository.getUserProfile()
            if(user is FirebaseGetResponse.Success) {
                userLocalRepository.setUserProfile(user.data)
                return FirebaseGetResponse.Success(user.data)
            }
            return FirebaseGetResponse.Error("Something went wrong")
        }
        return FirebaseGetResponse.Success(cache)
    }
}