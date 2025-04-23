package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.data.dto.lessons.ParentInfo
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor

class MainStudentViewModel(
    private val lessonsInteractor: LessonsInteractor
) : BaseViewModel<MainStudentStates, MainStudentActions>(MainStudentStates.Loading) {
    override fun submitAction(action: MainStudentActions) {
        when(action) {
            is MainStudentActions.LoadData -> loadData()
            is MainStudentActions.OpenCourses -> _state.value = MainStudentStates.EnterCourse(
                ParentInfo(id = action.levelInfo.folderId, name = action.levelInfo.name)
            )
        }
    }

    private fun loadData() {
        launch {
            try {
                _state.value = MainStudentStates.Loading
                val result = lessonsInteractor.getLevels()
                _state.value = MainStudentStates.ShowLevels(result)
            } catch (t: Throwable) {
                _state.value = MainStudentStates.Error(t.message.toString())
            }
        }
    }
}