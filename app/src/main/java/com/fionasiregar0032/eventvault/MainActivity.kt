package com.fionasiregar0032.eventvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fionasiregar0032.eventvault.ui.screen.MainScreen
import com.fionasiregar0032.eventvault.ui.theme.EventVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventVaultTheme {
                MainScreen()
            }
        }
    }
}

