package com.example.animeapp.presentaion.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val useCases: UseCases
): ViewModel() {

    private val _favoriteHeroes = MutableStateFlow<List<Hero>>(emptyList())
    val favoriteHeroes: StateFlow<List<Hero>> = _favoriteHeroes

    init {
        getFavoriteHeroes()
    }

    private fun getFavoriteHeroes() {
        viewModelScope.launch {
            useCases.getFavoritesUseCase().collect { heroes ->
                _favoriteHeroes.value = heroes
            }
        }
    }

    fun toggleFavorite(heroId: Int) {
        viewModelScope.launch {
            useCases.toggleFavoriteUseCase(heroId)
        }
    }
}

