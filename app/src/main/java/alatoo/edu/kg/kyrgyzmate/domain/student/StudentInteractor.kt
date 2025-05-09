package alatoo.edu.kg.kyrgyzmate.domain.student

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FireBasePostResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import alatoo.edu.kg.kyrgyzmate.data.student_data.StudentLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.student_data.StudentRestRepository

class StudentInteractor(
    private val studentLocalRepository: StudentLocalRepository,
    private val studentRestRepository: StudentRestRepository
) {

    suspend fun getUserProfile(refresh: Boolean = false): FirebaseGetResponse<StudentProfile?> {
        val cache = studentLocalRepository.getStudentProfile()
        if(cache == null || refresh) {
            val profile = studentRestRepository.getStudentProfile()
            val groupInfo = studentRestRepository.getStudentGroupInfo()
            if(profile is FirebaseGetResponse.Success && groupInfo is FirebaseGetResponse.Success) {
                val updatedProfile = profile.data?.copy(studentGroupInfo = groupInfo.data)
                return FirebaseGetResponse.Success(updatedProfile)
            }
            return FirebaseGetResponse.Error("Something went wrong")
        }
        return FirebaseGetResponse.Success(cache)
    }

    suspend fun getGroupList(): FirebaseGetResponse<List<GroupItem>> {
        val response = studentRestRepository.getGroupsList()
        return response
    }

    suspend fun updateProfile(
        name: String,
        surname: String,
        groupItem: GroupItem?
    ): FireBasePostResponse {
        return studentRestRepository.updateProfile(name, surname, groupItem)
    }
}