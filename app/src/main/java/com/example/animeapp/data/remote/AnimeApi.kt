package com.example.animeapp.data.remote

import com.example.animeapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("/anime/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int,
        @Query("category") category: String? = null
    ): ApiResponse

    @GET("/anime/heroes/categories")
    suspend fun getAllHeroesByCategories(
        @Query("page") page: Int,
        @Query("categories") categories: String
    ): ApiResponse

    @GET("/anime/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse
}