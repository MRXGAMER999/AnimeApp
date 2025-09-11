package com.example.animeapp.di

import com.example.animeapp.data.repository.DataStoreOperationsImpl
import com.example.animeapp.data.repository.Repository
import com.example.animeapp.data.use_cases.UseCases
import com.example.animeapp.data.use_cases.read_onborading.ReadOnBoardingUseCase
import com.example.animeapp.data.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.animeapp.domain.model.DataStoreOperations
import org.koin.dsl.module

val repositoryModule = module {
    single<DataStoreOperations> { 
        DataStoreOperationsImpl(context = get()) 
    }
    
    single { 
        Repository(dataStore = get()) 
    }
    
    single { 
        UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(get()),
            readOnBoardingUseCase = ReadOnBoardingUseCase(get())
        )
    }
}