package com.example.animeapp.presentaion.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    DetailsContent(selectedHero = selectedHero){
        onNavigateBack()
    }

}