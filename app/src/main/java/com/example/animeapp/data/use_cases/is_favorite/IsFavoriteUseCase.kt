package com.example.animeapp.data.use_cases.is_favorite

import com.example.animeapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class IsFavoriteUseCase(
    private val repository: Repository
) {
    operator fun invoke(heroId: Int): Flow<Boolean> {
        return repository.isFavorite(heroId)
    }
}

