package alatoo.edu.kg.kyrgyzmate

import alatoo.edu.kg.kyrgyzmate.core.BaseUrls
import alatoo.edu.kg.kyrgyzmate.services.DocsReader
import alatoo.edu.kg.kyrgyzmate.services.DriveApi
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("MainActivity", "{${DriveApi.listChildren(BaseUrls.rootId)}")
                Log.d("MainActivity", "{${DocsReader.readTextFile("19Kf4L7kG_G-sVfDhLCljigxHu_gMnx689zvLZeVaODw")}}")
            }
        }
    }
}
