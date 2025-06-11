package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile

interface StudentLocalRepository {

    fun setStudentProfile(studentProfile: StudentProfile)

    fun getStudentProfile(): StudentProfile?

    fun setStudentLevel(level: String)

    fun getStudentLevel(): String?

    fun setStudentTheme(theme: String)

    fun getStudentTheme(): String?

    fun setStudentTopic(topic: String)

    fun getStudentTopic(): String?
}