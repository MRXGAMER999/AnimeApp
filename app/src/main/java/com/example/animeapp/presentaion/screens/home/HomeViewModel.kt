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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

class HomeViewModel(
    useCases: UseCases
): ViewModel() {
    private val _selectedCategory = MutableStateFlow("Boruto")
    val selectedCategory: StateFlow<String> = _selectedCategory

    @OptIn(ExperimentalCoroutinesApi::class)
    val getAllHeroes: Flow<PagingData<Hero>> = _selectedCategory.flatMapLatest { category ->
        useCases.getAllHeroesUseCase(category = category)
    }.cachedIn(viewModelScope)

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }
}