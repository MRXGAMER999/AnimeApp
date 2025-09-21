package com.example.animeapp.presentaion.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.animeapp.util.Constants.BASE_URL
import com.example.animeapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.example.animeapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest
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

    LaunchedEffect(selectedHero) {
        if (selectedHero != null && colorPalette.isEmpty()) {
            detailsViewModel.generateColorsPalette()
        }
    }

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            selectedHero = selectedHero,
            colors = colorPalette,
            onCloseClicked = {
                onNavigateBack()
            }
        )
    }

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorsPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }
}