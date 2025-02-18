package alatoo.edu.kg.kyrgyzmate.application

import alatoo.edu.kg.kyrgyzmate.modules.authModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(authModule)
            )
            androidContext(this@MainApplication)
        }
    }
}
