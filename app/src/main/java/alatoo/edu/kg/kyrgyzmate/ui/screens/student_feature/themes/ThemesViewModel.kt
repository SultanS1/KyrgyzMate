package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes

import alatoo.edu.kg.kyrgyzmate.core.BaseViewModel
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor
import alatoo.edu.kg.kyrgyzmate.domain.student.StudentInteractor

class ThemesViewModel(
    private val lessonsInteractor: LessonsInteractor,
    private val studentInteractor: StudentInteractor
) : BaseViewModel<ThemesStates, ThemesActions>(ThemesStates.Loading) {

    override fun submitAction(action: ThemesActions) {
        when(action) {
            is ThemesActions.LoadThemes -> loadData(action.levelId)
            is ThemesActions.OpenTopics -> {
                studentInteractor.setStudentTheme(action.themeType.name)
                _state.value = ThemesStates.NavigateToTopics(action.themeType)
            }
            is ThemesActions.Popup -> _state.value = ThemesStates.Popup
        }
    }

    private fun loadData(levelId: String) {
        launch {
            try {
                _state.value = ThemesStates.Loading
                val result = lessonsInteractor.getThemes(levelId)
                _state.value = ThemesStates.ShowThemes(result)
            } catch (t: Throwable) {
                _state.value = ThemesStates.Error(t.message.toString())
            }
        }
    }
}