package com.example.animeapp.presentaion.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val useCases: UseCases,
    heroId: Int
): ViewModel() {
    private val _selectedHero = MutableStateFlow<Hero?>(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        Log.d("DetailsViewModel", "Creating DetailsViewModel for heroId: $heroId")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("DetailsViewModel", "Fetching hero with id: $heroId")
                _selectedHero.value = useCases.getSelectedHeroUseCase(heroId = heroId)
                Log.d("DetailsViewModel", "Selected hero: ${_selectedHero.value?.name} (id: ${_selectedHero.value?.id})")

            } catch (e: Exception) {
                Log.e("DetailsViewModel", "Error fetching hero: ${e.message}")
                // Handle error case
                _selectedHero.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }
}