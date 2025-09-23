package com.example.animeapp.presentaion.screens.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.data.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCases: UseCases
): ViewModel() {
    
    val selectedCategory: StateFlow<String> = useCases.readSelectedCategoryUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Boruto"
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val getAllHeroes: Flow<PagingData<Hero>> = selectedCategory.flatMapLatest { category ->
        useCases.getAllHeroesUseCase(category = category)
    }.cachedIn(viewModelScope)

    fun selectCategory(category: String) {
        viewModelScope.launch {
            useCases.saveSelectedCategoryUseCase(category = category)
        }
    }
}