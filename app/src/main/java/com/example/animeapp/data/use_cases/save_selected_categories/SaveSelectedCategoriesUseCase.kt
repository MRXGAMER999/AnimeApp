package com.example.animeapp.data.use_cases.save_selected_categories

import com.example.animeapp.data.repository.Repository

class SaveSelectedCategoriesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(categories: Set<String>) {
        repository.saveSelectedCategories(categories = categories)
    }
}
