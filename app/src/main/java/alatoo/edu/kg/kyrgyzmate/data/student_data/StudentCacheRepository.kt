package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import kotlinx.coroutines.flow.MutableStateFlow

class StudentCacheRepository : StudentLocalRepository {

    private val studentProfileFlow: MutableStateFlow<StudentProfile?> = MutableStateFlow(null)

    override fun setStudentProfile(studentProfile: StudentProfile) {
        studentProfileFlow.value = studentProfile
    }

    override fun getStudentProfile(): StudentProfile? {
        return studentProfileFlow.value
    }
}