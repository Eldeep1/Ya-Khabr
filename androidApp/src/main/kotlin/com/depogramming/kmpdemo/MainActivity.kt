package com.depogramming.kmpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.depogramming.kmpdemo.data.local.setApplicationContext
import android.app.Application
import com.depogramming.kmpdemo.data.local.appContext
import com.depogramming.kmpdemo.di.initKoin
import org.koin.android.ext.koin.androidContext

class KMPDemo: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        initKoin {
            androidContext(this@KMPDemo)
        }
    }
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setApplicationContext(applicationContext)
        setContent {
            App()
        }
    }
}
