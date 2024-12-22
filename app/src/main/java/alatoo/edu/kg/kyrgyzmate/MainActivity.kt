package alatoo.edu.kg.kyrgyzmate

import alatoo.edu.kg.kyrgyzmate.ui.theme.SplashBackground
import alatoo.edu.kg.kyrgyzmate.ui.theme.Typography
import alatoo.edu.kg.kyrgyzmate.ui.theme.White
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SplashBackground),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.kyrgyzmate),
                    style = Typography.headlineLarge,
                    color = White,
                    modifier = Modifier.padding(8.dp), 
                )
            }
        }
    }
}