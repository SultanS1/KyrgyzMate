package alatoo.edu.kg.kyrgyzmate.application

import alatoo.edu.kg.kyrgyzmate.modules.authModule
import alatoo.edu.kg.kyrgyzmate.modules.lessonsModule
import alatoo.edu.kg.kyrgyzmate.modules.studentModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(authModule, lessonsModule, studentModule)
            )
            androidContext(this@MainApplication)
        }
    }
}
