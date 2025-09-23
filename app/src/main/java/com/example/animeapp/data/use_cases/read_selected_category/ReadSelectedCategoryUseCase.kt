package com.example.animeapp.data.use_cases.read_selected_category

import com.example.animeapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadSelectedCategoryUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<String> {
        return repository.readSelectedCategories().map { it.firstOrNull() ?: "" }
    }
}
