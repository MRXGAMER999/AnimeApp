package com.example.animeapp.data.use_cases

import com.example.animeapp.data.use_cases.read_onborading.ReadOnBoardingUseCase
import com.example.animeapp.data.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase

)