package com.example.animeapp.data.repository

import androidx.paging.PagingData
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.domain.repository.DataStoreOperations
import com.example.animeapp.domain.repository.LocalDataSource
import com.example.animeapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class Repository(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations,
    private val local: LocalDataSource
) {
     fun getAllHeroes(category:  Set<String>): Flow<PagingData<Hero>> {
        return remote.getAllHeroes(category = category)

    }
    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query = query)
    }
    suspend fun getSelectedHero(heroId: Int): Hero {
        return local.getSelectedHero(heroId = heroId)
    }
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }


    suspend fun saveSelectedCategories(categories: Set<String>) {
        dataStore.saveSelectedCategories(categories = categories)
    }

    fun readSelectedCategories(): Flow<Set<String>> {
        return dataStore.readSelectedCategories()
    }

    // Favorite operations
    fun getAllFavoriteHeroes(): Flow<List<Hero>> {
        return local.getAllFavoriteHeroes()
    }

    fun isFavorite(heroId: Int): Flow<Boolean> {
        return local.isFavorite(heroId)
    }

    suspend fun addFavorite(heroId: Int) {
        local.addFavorite(heroId)
    }

    suspend fun removeFavorite(heroId: Int) {
        local.removeFavorite(heroId)
    }

    suspend fun toggleFavorite(heroId: Int) {
        local.toggleFavorite(heroId)
    }
}