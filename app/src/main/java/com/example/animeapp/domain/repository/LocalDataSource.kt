package com.example.animeapp.domain.repository

import com.example.animeapp.domain.model.Hero
import com.example.animeapp.domain.model.FavoriteHero
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero

    // Favorite operations
    fun getAllFavoriteHeroes(): Flow<List<Hero>>
    fun isFavorite(heroId: Int): Flow<Boolean>
    suspend fun addFavorite(heroId: Int)
    suspend fun removeFavorite(heroId: Int)
    suspend fun toggleFavorite(heroId: Int)
}