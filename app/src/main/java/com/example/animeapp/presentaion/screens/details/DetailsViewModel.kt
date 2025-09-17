package com.example.animeapp.presentaion.screens.details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val useCases: UseCases,
    heroId: Int
): ViewModel() {
    private val _selectedHero = mutableStateOf<Hero?>(null)
    val selectedHero: State<Hero?> = _selectedHero

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _selectedHero.value = useCases.getSelectedHeroUseCase(heroId = heroId)
                Log.d("DetailsViewModel", "Selected hero: ${_selectedHero.value}")
            } catch (e: Exception) {
                // Handle error case
                _selectedHero.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}