package alatoo.edu.kg.kyrgyzmate.modules

import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsCacheRepository
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsRemoteRepository
import alatoo.edu.kg.kyrgyzmate.data.lessons.LessonsRestRepository
import alatoo.edu.kg.kyrgyzmate.domain.lesson.LessonsInteractor
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.dialog.DialogViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.texts.TextsViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.themes.ThemesViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.topics.TopicsViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.words.WordsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val lessonsModule = module {
    single<LessonsRestRepository> { LessonsRemoteRepository() }
    single<LessonsLocalRepository> { LessonsCacheRepository() }
    single { LessonsInteractor(get(), get(), androidContext())  }

    viewModelOf(::ThemesViewModel)
    viewModelOf(::TopicsViewModel)
    viewModelOf(::DialogViewModel)
    viewModelOf(::WordsViewModel)
    viewModelOf(::TextsViewModel)
}