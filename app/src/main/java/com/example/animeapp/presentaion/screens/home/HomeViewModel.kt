package com.example.animeapp.presentaion.screens.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.data.use_cases.get_all_heroes.GetAllHeroesUseCase

class HomeViewModel(
    useCases: UseCases
): ViewModel() {

    val getAllHeroes = useCases.getAllHeroesUseCase().cachedIn(viewModelScope)
}