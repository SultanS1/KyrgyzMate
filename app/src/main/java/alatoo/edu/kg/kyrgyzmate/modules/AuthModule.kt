package alatoo.edu.kg.kyrgyzmate.modules

import alatoo.edu.kg.kyrgyzmate.ui.screens.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {

    viewModelOf(::LoginViewModel)
}