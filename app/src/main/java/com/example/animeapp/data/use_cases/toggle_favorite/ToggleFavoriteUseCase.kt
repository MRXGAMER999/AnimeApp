package com.example.animeapp.data.use_cases.toggle_favorite

import com.example.animeapp.data.repository.Repository

class ToggleFavoriteUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int) {
        repository.toggleFavorite(heroId)
    }
}

