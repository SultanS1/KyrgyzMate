package alatoo.edu.kg.kyrgyzmate.modules

import alatoo.edu.kg.kyrgyzmate.data.user_data.UserCacheRepository
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserLocalRepository
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserRemoteRepository
import alatoo.edu.kg.kyrgyzmate.data.user_data.UserRestRepository
import alatoo.edu.kg.kyrgyzmate.domain.user.UserInteractor
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password.CreatePasswordViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login.LoginViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration.RegistrationViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash.SplashViewModel
import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

const val APP_PREFS = "APP_PREFS"

val authModule = module {
    single { FirebaseAuth.getInstance() }
    single { Firebase.database.reference }
    single { androidContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE) }
    single<UserRestRepository> { UserRemoteRepository(get(), get()) }
    single<UserLocalRepository> { UserCacheRepository(get())}
    single { UserInteractor(get(), get())  }

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::CreatePasswordViewModel)
    viewModelOf(::SplashViewModel)
}