package com.example.animeapp.data.use_cases.getSelectedHero

import com.example.animeapp.data.repository.Repository
import com.example.animeapp.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository

) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)

    }
}