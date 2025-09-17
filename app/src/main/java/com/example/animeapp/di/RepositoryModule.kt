package com.example.animeapp.di

import com.example.animeapp.data.repository.DataStoreOperationsImpl
import com.example.animeapp.data.repository.Repository
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.data.use_cases.getSelectedHero.GetSelectedHeroUseCase
import com.example.animeapp.data.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.animeapp.data.use_cases.read_onborading.ReadOnBoardingUseCase
import com.example.animeapp.data.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.animeapp.data.use_cases.search_heroes.SearchHeroesUseCase
import com.example.animeapp.domain.repository.DataStoreOperations
import org.koin.dsl.module

val repositoryModule = module {
    single<DataStoreOperations> { 
        DataStoreOperationsImpl(context = get()) 
    }
    
    single { 
        Repository(dataStore = get(),
            remote = get(),
            local = get())
    }
    
    single { 
        UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(get()),
            readOnBoardingUseCase = ReadOnBoardingUseCase(get()),
            getAllHeroesUseCase = GetAllHeroesUseCase(get()),
            searchHeroesUseCase = SearchHeroesUseCase(get()),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(get())
        )
    }
}