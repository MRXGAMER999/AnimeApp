package com.example.animeapp.presentaion.screens.details

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.animeapp.util.Constants.BASE_URL
import com.example.animeapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.example.animeapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeoutOrNull
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(
    heroId: Int,
    onNavigateBack: () -> Unit = { },
    detailsViewModel: DetailsViewModel = koinViewModel(key = heroId.toString()) { parametersOf(heroId) }
){
    BackHandler {
        onNavigateBack()
    }


    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette
    val context = LocalContext.current
    
    // Track when palette generation is complete
    var paletteReady by remember { mutableStateOf(false) }

    LaunchedEffect(selectedHero) {
        selectedHero?.let { hero ->
            paletteReady = false // Reset for new hero
            try {
                Log.d("DetailsScreen", "Generating palette for hero: ${hero.name}")
                val imageUrl = "$BASE_URL${hero.image}"
                
                // Add timeout to prevent hanging operations
                val result = withTimeoutOrNull(10_000L) { // 10 second timeout
                    if (isActive) { // Check if coroutine is still active
                        val bitmap = convertImageUrlToBitmap(
                            imageUrl = imageUrl,
                            context = context
                        )
                        if (bitmap != null && isActive) {
                            extractColorsFromBitmap(bitmap)
                        } else null
                    } else null
                }
                
                if (isActive) { // Only update state if still active
                    if (result != null) {
                        Log.d("DetailsScreen", "Successfully generated palette: $result")
                        detailsViewModel.setColorPalette(colors = result)
                    } else {
                        Log.w("DetailsScreen", "Failed to generate palette or timed out for image: $imageUrl")
                        // Set fallback colors when generation fails or times out
                        detailsViewModel.setColorPalette(
                            colors = mapOf(
                                "vibrant" to "#000000",
                                "darkVibrant" to "#000000", 
                                "onDarkVibrant" to "#FFFFFF"
                            )
                        )
                    }
                }
            } catch (e: CancellationException) {
                Log.d("DetailsScreen", "Palette generation cancelled for hero: ${hero.name}")
                // Don't set state on cancellation - composable is likely gone
                throw e // Re-throw to properly handle cancellation
            } catch (e: Exception) {
                Log.e("DetailsScreen", "Error generating color palette", e)
                if (isActive) { // Only update state if still active
                    // Set fallback colors on error
                    detailsViewModel.setColorPalette(
                        colors = mapOf(
                            "vibrant" to "#000000",
                            "darkVibrant" to "#000000", 
                            "onDarkVibrant" to "#FFFFFF"
                        )
                    )
                }
            } finally {
                if (isActive) {
                    paletteReady = true
                }
            }
        }
    }

    // Show content when both hero and palette are ready
    if (selectedHero != null && paletteReady) {
        DetailsContent(
            selectedHero = selectedHero,
            colors = colorPalette, // Now guaranteed to be set
            onCloseClicked = {
                onNavigateBack()
            }
        )
    } else if (selectedHero != null) {
        // Show loading indicator while generating palette
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}