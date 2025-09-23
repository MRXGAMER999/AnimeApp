package com.example.animeapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animeapp.domain.model.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_table WHERE category = :category ORDER BY id ASC")
    fun getAllHeroesByCategory(category: String): PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_table WHERE category IN (:categories) ORDER BY id ASC")
    fun getAllHeroesByCategories(categories: List<String>): PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_table WHERE category = :category ORDER BY id ASC")
    suspend fun getHeroesByCategoryList(category: String): List<Hero>

    @Query("SELECT id FROM hero_table WHERE category = :category ORDER BY id ASC LIMIT 1")
    suspend fun getFirstHeroIdByCategory(category: String): Int?

    @Query("SELECT * FROM hero_table WHERE id =:heroId")
    suspend fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes : List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()

    @Query("DELETE FROM hero_table WHERE category = :category")
    suspend fun deleteHeroesByCategory(category: String)

}