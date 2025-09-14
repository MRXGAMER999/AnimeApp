package com.example.animeapp.di

import com.example.animeapp.presentaion.screens.home.HomeViewModel
import com.example.animeapp.presentaion.screens.splash.SplashViewModel
import com.example.animeapp.presentaion.screens.welcome.WelcomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WelcomeViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}
