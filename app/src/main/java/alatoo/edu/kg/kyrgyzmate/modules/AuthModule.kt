package alatoo.edu.kg.kyrgyzmate.modules

import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.create_password.CreatePasswordViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login.LoginViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration.RegistrationPageAction
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration.RegistrationPageStates
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.registration.RegistrationViewModel
import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::CreatePasswordViewModel)
    viewModelOf(::SplashViewModel)
}