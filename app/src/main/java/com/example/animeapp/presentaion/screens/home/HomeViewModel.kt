package com.example.animeapp.presentaion.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import com.example.animeapp.util.Constants

class HomeViewModel(
    private val useCases: UseCases
): ViewModel() {
    

    val selectedCategories: StateFlow<Set<String>> = useCases.readSelectedCategoriesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Constants.DEFAULT_SELECTED_CATEGORIES
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val getAllHeroes: Flow<PagingData<Hero>> = selectedCategories.flatMapLatest { categories ->
        useCases.getAllHeroesUseCase(category = categories)
    }.cachedIn(viewModelScope)

}