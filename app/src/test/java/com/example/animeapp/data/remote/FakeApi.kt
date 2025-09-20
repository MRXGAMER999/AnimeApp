package com.example.animeapp.data.remote

import com.example.animeapp.domain.model.ApiResponse
import com.example.animeapp.domain.model.Hero

class FakeApi: AnimeApi {

    private val heroes = listOf(
        Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 2,
            name = "Naruto",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 3,
            name = "Sakura",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
    )

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = true
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        val searchedHeroes = findHeroes(name)
        return ApiResponse(
            success = true,
            message = "ok",
            heroes = searchedHeroes
        )
    }

    private fun findHeroes(query: String): List<Hero> {
        val result = mutableListOf<Hero>()
        return if(query.isNotEmpty()){
            heroes.forEach { hero ->
                if(hero.name.lowercase().contains(query.lowercase())){
                    result.add(hero)
                }
            }
            result
        } else {
            emptyList()
        }
    }
}