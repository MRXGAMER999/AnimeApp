package com.example.animeapp.presentaion.screens.details

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(
    heroId: Int,
    onNavigateBack: () -> Unit = { },
    detailsViewModel: DetailsViewModel = koinViewModel { parametersOf(heroId) }
){
    val selectedHero = detailsViewModel.selectedHero
}