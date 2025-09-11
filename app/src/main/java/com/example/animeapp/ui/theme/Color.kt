package com.example.animeapp.ui.theme

import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Purple variants
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

// Teal variant
val Teal200 = Color(0xFF03DAC5)

// Custom colors
val DarkBlue = Color(0xFF211C84)
val MediumBlue = Color(0xFF4D55CC)
val LightBlue = Color(0xFF7A73D1)
val LightPurple = Color(0xFFB5A8D5)

// Additional custom colors
val DarkNavy = Color(0xFF070F2B)
val DarkIndigo = Color(0xFF1B1A55)
val SlateBlue = Color(0xFF535C91)
val LightSlate = Color(0xFF9290C3)

// Theme-based background brushes
@Composable
fun getThemeBasedGradient(): Brush {
    return if (isSystemInDarkTheme()) {
        Brush.verticalGradient(listOf(DarkNavy, DarkIndigo))
    } else {
        Brush.verticalGradient(listOf(LightBlue,DarkBlue ))
    }
}

// Alternative: More customizable version
@Composable
fun getGradientForTheme(
    darkColors: List<Color> = listOf(DarkNavy, DarkIndigo),
    lightColors: List<Color> = listOf(DarkBlue, LightBlue)
): Brush {
    return if (isSystemInDarkTheme()) {
        Brush.verticalGradient(darkColors)
    } else {
        Brush.verticalGradient(lightColors)
    }
}

// Theme-based button colors
@Composable
fun getThemeBasedButtonColors(): ButtonColors {
    return if (isSystemInDarkTheme()) {
        ButtonDefaults.buttonColors(
            containerColor = LightSlate,
            contentColor = DarkNavy,
            disabledContainerColor = LightSlate.copy(alpha = 0.6f),
            disabledContentColor = DarkNavy.copy(alpha = 0.6f)
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = DarkBlue,
            contentColor = Color.White,
            disabledContainerColor = DarkBlue.copy(alpha = 0.6f),
            disabledContentColor = Color.White.copy(alpha = 0.6f)
        )
    }
}

// Alternative: More customizable version
@Composable
fun getButtonColorsForTheme(
    darkContainerColor: Color = LightSlate,
    darkContentColor: Color = DarkNavy,
    lightContainerColor: Color = DarkBlue,
    lightContentColor: Color = Color.White
): ButtonColors {
    return if (isSystemInDarkTheme()) {
        ButtonDefaults.buttonColors(
            containerColor = darkContainerColor,
            contentColor = darkContentColor,
            disabledContainerColor = darkContainerColor.copy(alpha = 0.6f),
            disabledContentColor = darkContentColor.copy(alpha = 0.6f)
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = lightContainerColor,
            contentColor = lightContentColor,
            disabledContainerColor = lightContainerColor.copy(alpha = 0.6f),
            disabledContentColor = lightContentColor.copy(alpha = 0.6f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getThemeBasedTopAppBarColors(): TopAppBarColors {
    return if (isSystemInDarkTheme()) {
        TopAppBarDefaults.topAppBarColors(
            containerColor = DarkIndigo.copy(alpha = 0.9f),
            titleContentColor = Color.White,
            actionIconContentColor = LightSlate,
            navigationIconContentColor = Color.White,
            scrolledContainerColor = DarkNavy.copy(alpha = 0.95f)
        )
    } else {
        TopAppBarDefaults.topAppBarColors(
            containerColor = LightPurple.copy(alpha = 0.3f),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White.copy(alpha = 0.8f),
            navigationIconContentColor = Color.White,
            scrolledContainerColor = MediumBlue.copy(alpha = 0.95f)
        )
    }
}

