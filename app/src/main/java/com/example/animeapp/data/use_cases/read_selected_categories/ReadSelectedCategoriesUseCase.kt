package com.example.animeapp.data.use_cases.read_selected_categories

import com.example.animeapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadSelectedCategoriesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.readSelectedCategories()
    }
}
