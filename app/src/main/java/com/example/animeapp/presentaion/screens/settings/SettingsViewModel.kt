package com.example.animeapp.presentaion.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.util.Constants
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val useCases: UseCases
) : ViewModel() {


    val selectedCategories: StateFlow<Set<String>> = useCases.readSelectedCategoriesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Constants.DEFAULT_SELECTED_CATEGORIES
    )

    fun toggleCategory(category: String) {
        viewModelScope.launch {
            val currentCategories = selectedCategories.value.toMutableSet()
            if (currentCategories.contains(category)) {
                currentCategories.remove(category)
            } else {
                currentCategories.add(category)
            }
            // Ensure only valid categories are saved
            val sanitized = currentCategories.intersect(Constants.AVAILABLE_CATEGORIES.toSet())
            useCases.saveSelectedCategoriesUseCase(categories = sanitized)
        }
    }
}
