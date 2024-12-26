package alatoo.edu.kg.kyrgyzmate

import alatoo.edu.kg.kyrgyzmate.ui.screens.student.home.main.components.MainScreenContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent()
        }
    }
}