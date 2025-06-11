package alatoo.edu.kg.kyrgyzmate.modules

import alatoo.edu.kg.kyrgyzmate.data.lecturer_data.LecturerCacheRepository
import alatoo.edu.kg.kyrgyzmate.data.lecturer_data.LecturerLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.lecturer_data.LecturerRemoteRepository
import alatoo.edu.kg.kyrgyzmate.data.lecturer_data.LecturerRestRepository
import alatoo.edu.kg.kyrgyzmate.domain.lecturer.LecturerInteractor
import alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.groups.GroupsViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.home.profile.LecturerProfileViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.lecturer_feature.new_group.CreateNewGroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val lecturerModule = module {
    single<LecturerRestRepository> { LecturerRemoteRepository(get(), get()) }
    single<LecturerLocalRepository> { LecturerCacheRepository() }
    single { LecturerInteractor(get(), get(), get(), get())  }

    viewModelOf(::GroupsViewModel)
    viewModelOf(::CreateNewGroupViewModel)
    viewModelOf(::LecturerProfileViewModel)
}