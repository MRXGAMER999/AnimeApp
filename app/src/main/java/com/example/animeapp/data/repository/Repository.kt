package com.example.animeapp.data.repository

import com.example.animeapp.domain.model.DataStoreOperations
import kotlinx.coroutines.flow.Flow

class Repository(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}