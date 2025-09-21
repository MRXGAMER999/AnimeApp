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
     fun getAllHeroes(category: String?): Flow<PagingData<Hero>> {
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
}