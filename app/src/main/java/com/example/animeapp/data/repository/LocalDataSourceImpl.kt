package com.example.animeapp.data.repository

import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.domain.model.FavoriteHero
import com.example.animeapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow




class LocalDataSourceImpl(animeDatabase: AnimeDatabase): LocalDataSource {
    private val heroDao = animeDatabase.heroDao()
    private val favoriteHeroDao = animeDatabase.favoriteHeroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

    override fun getAllFavoriteHeroes(): Flow<List<Hero>> {
        return favoriteHeroDao.getAllFavoriteHeroesDetails()
    }

    override fun isFavorite(heroId: Int): Flow<Boolean> {
        return favoriteHeroDao.isFavorite(heroId)
    }

    override suspend fun addFavorite(heroId: Int) {
        favoriteHeroDao.addFavorite(FavoriteHero(heroId = heroId))
    }

    override suspend fun removeFavorite(heroId: Int) {
        favoriteHeroDao.removeFavorite(heroId)
    }

    override suspend fun toggleFavorite(heroId: Int) {
        if (favoriteHeroDao.isFavoriteSync(heroId)) {
            favoriteHeroDao.removeFavorite(heroId)
        } else {
            favoriteHeroDao.addFavorite(FavoriteHero(heroId = heroId))
        }
    }
}