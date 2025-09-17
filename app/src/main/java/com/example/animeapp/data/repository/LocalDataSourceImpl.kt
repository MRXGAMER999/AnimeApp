package com.example.animeapp.data.repository

import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.domain.repository.LocalDataSource




class LocalDataSourceImpl(animeDatabase: AnimeDatabase): LocalDataSource {
    private val heroDao = animeDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

}