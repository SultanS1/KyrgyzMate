package alatoo.edu.kg.kyrgyzmate.modules

import alatoo.edu.kg.kyrgyzmate.data.student_data.StudentCacheRepository
import alatoo.edu.kg.kyrgyzmate.data.student_data.StudentLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.student_data.StudentRemoteRepository
import alatoo.edu.kg.kyrgyzmate.data.student_data.StudentRestRepository
import alatoo.edu.kg.kyrgyzmate.domain.student.StudentInteractor
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.main.MainStudentViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val studentModule = module {
    single<StudentRestRepository> { StudentRemoteRepository(get(), get()) }
    single<StudentLocalRepository> { StudentCacheRepository() }
    single { StudentInteractor(get(), get()) }
    viewModelOf(::MainStudentViewModel)
    viewModelOf(::ProfileViewModel)
}