package com.example.animeapp.data.use_cases

import com.example.animeapp.data.use_cases.getSelectedHero.GetSelectedHeroUseCase
import com.example.animeapp.data.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.animeapp.data.use_cases.get_favorites.GetFavoritesUseCase
import com.example.animeapp.data.use_cases.is_favorite.IsFavoriteUseCase
import com.example.animeapp.data.use_cases.read_onborading.ReadOnBoardingUseCase
import com.example.animeapp.data.use_cases.read_selected_categories.ReadSelectedCategoriesUseCase
import com.example.animeapp.data.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.animeapp.data.use_cases.save_selected_categories.SaveSelectedCategoriesUseCase
import com.example.animeapp.data.use_cases.search_heroes.SearchHeroesUseCase
import com.example.animeapp.data.use_cases.toggle_favorite.ToggleFavoriteUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val saveSelectedCategoriesUseCase: SaveSelectedCategoriesUseCase,
    val readSelectedCategoriesUseCase: ReadSelectedCategoriesUseCase,
    val getFavoritesUseCase: GetFavoritesUseCase,
    val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    val isFavoriteUseCase: IsFavoriteUseCase
)