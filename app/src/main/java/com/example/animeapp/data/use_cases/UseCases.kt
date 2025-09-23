package com.example.animeapp.data.use_cases

import com.example.animeapp.data.use_cases.getSelectedHero.GetSelectedHeroUseCase
import com.example.animeapp.data.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.animeapp.data.use_cases.read_onborading.ReadOnBoardingUseCase
import com.example.animeapp.data.use_cases.read_selected_category.ReadSelectedCategoryUseCase
import com.example.animeapp.data.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.animeapp.data.use_cases.save_selected_category.SaveSelectedCategoryUseCase
import com.example.animeapp.data.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val saveSelectedCategoryUseCase: SaveSelectedCategoryUseCase,
    val readSelectedCategoryUseCase: ReadSelectedCategoryUseCase
)