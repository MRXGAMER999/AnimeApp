package com.example.animeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animeapp.util.Constants.FAVORITE_HERO_DATABASE_TABLE

@Entity(tableName = FAVORITE_HERO_DATABASE_TABLE)
data class FavoriteHero(
    @PrimaryKey(autoGenerate = false)
    val heroId: Int,
    val timestamp: Long = System.currentTimeMillis()
)

