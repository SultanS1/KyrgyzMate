package alatoo.edu.kg.kyrgyzmate.data.student_data

import alatoo.edu.kg.kyrgyzmate.data.dto.student.StudentProfile
import kotlinx.coroutines.flow.MutableStateFlow

class StudentCacheRepository : StudentLocalRepository {

    private val studentProfileFlow: MutableStateFlow<StudentProfile?> = MutableStateFlow(null)

    private val studentLevelFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    private val studentThemeFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    private val studentTopicFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun setStudentProfile(studentProfile: StudentProfile) {
        studentProfileFlow.value = studentProfile
    }

    override fun getStudentProfile(): StudentProfile? {
        return studentProfileFlow.value
    }

    override fun setStudentLevel(level: String) {
        studentLevelFlow.value = level
    }

    override fun getStudentLevel(): String? {
        return studentLevelFlow.value
    }

    override fun setStudentTheme(theme: String) {
        studentThemeFlow.value = theme
    }

    override fun getStudentTheme(): String? {
        return studentThemeFlow.value
    }

    override fun setStudentTopic(topic: String) {
        studentTopicFlow.value = topic
    }

    override fun getStudentTopic(): String? {
        return studentTopicFlow.value
    }
}