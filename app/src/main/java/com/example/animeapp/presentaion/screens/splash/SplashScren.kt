package com.example.animeapp.presentaion.screens.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.animeapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.animeapp.ui.theme.DarkBlue
import com.example.animeapp.ui.theme.DarkIndigo
import com.example.animeapp.ui.theme.DarkNavy
import com.example.animeapp.ui.theme.LightBlue
import com.example.animeapp.ui.theme.LightPurple
import com.example.animeapp.ui.theme.MediumBlue
import com.example.animeapp.ui.theme.Purple40
import com.example.animeapp.ui.theme.Purple80

@Composable
fun SplashScreen(){
    Splash()
}


@Composable
fun Splash(){
    if (isSystemInDarkTheme()){
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(DarkNavy, DarkIndigo)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash logo"
            )
        }
    } else{
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(DarkBlue, LightBlue)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash logo"
            )
        }
    }

}



@Preview(showSystemUi = true)
@Composable
fun SplashPreview(){
    Splash()
}