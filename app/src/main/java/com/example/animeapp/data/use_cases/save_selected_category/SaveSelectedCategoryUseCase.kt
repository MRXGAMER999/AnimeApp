package com.example.animeapp.data.use_cases.save_selected_category

import com.example.animeapp.data.repository.Repository

class SaveSelectedCategoryUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(category: String) {
        repository.saveSelectedCategory(category = category)
    }
}
