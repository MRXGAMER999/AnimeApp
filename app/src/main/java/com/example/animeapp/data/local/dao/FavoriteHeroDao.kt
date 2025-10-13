package com.example.animeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animeapp.domain.model.FavoriteHero
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteHeroDao {
    @Query("SELECT * FROM favorite_hero_table ORDER BY timestamp DESC")
    fun getAllFavoriteHeroes(): Flow<List<FavoriteHero>>

    @Query("SELECT * FROM hero_table WHERE id IN (SELECT heroId FROM favorite_hero_table ORDER BY timestamp DESC)")
    fun getAllFavoriteHeroesDetails(): Flow<List<Hero>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_hero_table WHERE heroId = :heroId)")
    fun isFavorite(heroId: Int): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_hero_table WHERE heroId = :heroId)")
    suspend fun isFavoriteSync(heroId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteHero)

    @Query("DELETE FROM favorite_hero_table WHERE heroId = :heroId")
    suspend fun removeFavorite(heroId: Int)

    @Query("DELETE FROM favorite_hero_table")
    suspend fun clearAllFavorites()
}

