package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile

interface StudentLocalRepository {

    fun setStudentProfile(studentProfile: StudentProfile)

    fun getStudentProfile(): StudentProfile?

}