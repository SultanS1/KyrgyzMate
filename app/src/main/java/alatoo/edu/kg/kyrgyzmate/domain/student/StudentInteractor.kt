package alatoo.edu.kg.kyrgyzmate.domain.student

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
            val response = studentRestRepository.getStudentProfile()
            if(response is FirebaseGetResponse.Success) {
                response.data?.let { studentLocalRepository.setStudentProfile(it) }
            }
            return response
        }
        return FirebaseGetResponse.Success(studentLocalRepository.getStudentProfile())
    }

}