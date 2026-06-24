package com.depogramming.kmpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.depogramming.kmpdemo.data.local.setApplicationContext

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
