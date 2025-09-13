package com.example.animeapp.data.repository

import androidx.paging.PagingData
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.domain.repository.DataStoreOperations
import com.example.animeapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class Repository(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {
     fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()

    }
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}