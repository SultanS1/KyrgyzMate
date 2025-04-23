package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.status.FirebaseGetResponse
import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile

interface StudentRestRepository {

    suspend fun getStudentProfile(): FirebaseGetResponse<StudentProfile?>

}