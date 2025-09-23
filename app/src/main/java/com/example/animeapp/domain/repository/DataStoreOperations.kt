package com.example.animeapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveSelectedCategory(category: String)
    fun readSelectedCategory(): Flow<String>
}