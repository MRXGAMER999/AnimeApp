package com.example.animeapp.data.use_cases.read_onborading

import com.example.animeapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow


class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}