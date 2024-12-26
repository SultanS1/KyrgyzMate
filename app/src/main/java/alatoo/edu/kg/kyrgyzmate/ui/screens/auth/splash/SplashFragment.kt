package alatoo.edu.kg.kyrgyzmate.ui.screens.auth.splash

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.ui.theme.SplashBackground
import alatoo.edu.kg.kyrgyzmate.ui.theme.White
import alatoo.edu.kg.kyrgyzmate.ui.theme.headlineLarge
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(SplashBackground),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.kyrgyzmate),
                        style = headlineLarge,
                        color = White,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    }
}
