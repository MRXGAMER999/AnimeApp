package com.example.animeapp.domain.model

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>

}