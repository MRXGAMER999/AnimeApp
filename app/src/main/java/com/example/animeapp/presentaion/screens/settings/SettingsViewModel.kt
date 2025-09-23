package com.example.animeapp.presentaion.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.use_cases.UseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val useCases: UseCases
) : ViewModel() {

    val selectedCategory: StateFlow<String> = useCases.readSelectedCategoryUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Boruto"
    )

    fun selectCategory(category: String) {
        viewModelScope.launch {
            useCases.saveSelectedCategoryUseCase(category = category)
        }
    }
}
