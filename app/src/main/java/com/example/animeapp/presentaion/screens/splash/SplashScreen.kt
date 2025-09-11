package com.example.animeapp.presentaion.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import com.example.animeapp.R
import androidx.compose.ui.res.painterResource
import com.example.animeapp.ui.theme.getThemeBasedGradient
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    onNavigateToWelcome: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
){
    val splashViewModel : SplashViewModel = koinViewModel()
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val degrees = remember { Animatable(0f) }
    
    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 720f,
            animationSpec = tween(
                durationMillis = 1500,
                delayMillis = 200
            )
        )


        kotlinx.coroutines.delay(1000)
        if (onBoardingCompleted) {
            onNavigateToHome()
        } else {
            onNavigateToWelcome()
        }
    }
    
    Splash(degrees = degrees.value)
}


@Composable
fun Splash(degrees: Float){
    Box(modifier = Modifier
        .background(getThemeBasedGradient())
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .rotate(degrees)
                .alpha(0.9f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Splash logo"
        )
    }
}



//@Preview(showSystemUi = true)
//@Composable
//fun SplashPreview(){
//    Splash(degrees = degrees)
//}