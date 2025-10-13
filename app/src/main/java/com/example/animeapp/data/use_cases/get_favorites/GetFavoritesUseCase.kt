package com.example.animeapp.data.use_cases.get_favorites

import com.example.animeapp.data.repository.Repository
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Hero>> {
        return repository.getAllFavoriteHeroes()
    }
}

