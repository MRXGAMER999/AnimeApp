package com.example.animeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import com.example.animeapp.navigation.NavigationRoot
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.animeapp.ui.theme.getThemeBasedGradient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeAppTheme {
                NavigationRoot(
                    modifier = Modifier.background(getThemeBasedGradient())
                )
            }
        }
    }
}

