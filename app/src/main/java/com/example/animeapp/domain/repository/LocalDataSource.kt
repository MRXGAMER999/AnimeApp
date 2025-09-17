package com.example.animeapp.domain.repository

import com.example.animeapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}