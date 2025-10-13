package com.example.animeapp.di

import com.example.animeapp.presentaion.screens.details.DetailsViewModel
import com.example.animeapp.presentaion.screens.favorites.FavoritesViewModel
import com.example.animeapp.presentaion.screens.home.HomeViewModel
import com.example.animeapp.presentaion.screens.search.SearchViewModel
import com.example.animeapp.presentaion.screens.settings.SettingsViewModel
import com.example.animeapp.presentaion.screens.splash.SplashViewModel
import com.example.animeapp.presentaion.screens.welcome.WelcomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WelcomeViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { (heroId: Int) ->
        DetailsViewModel(
            useCases = get(),
            heroId = heroId
        )
    }
}
