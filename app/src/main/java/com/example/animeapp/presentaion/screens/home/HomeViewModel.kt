package com.example.animeapp.presentaion.screens.home

import androidx.lifecycle.ViewModel
import com.example.animeapp.data.use_cases.UseCases

class HomeViewModel(
    useCases: UseCases
): ViewModel() {
    val getAllHeroes = useCases.getAllHeroesUseCase()
}