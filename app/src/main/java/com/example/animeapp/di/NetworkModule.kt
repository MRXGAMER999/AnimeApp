package com.example.animeapp.di

import com.example.animeapp.data.remote.AnimeApi
import com.example.animeapp.data.repository.RemoteDataSourceImpl
import com.example.animeapp.domain.repository.RemoteDataSource
import com.example.animeapp.util.Constants.BASE_URL
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }
    
    single<Retrofit> {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
    
    single<AnimeApi> {
        get<Retrofit>().create(AnimeApi::class.java)
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(
            animeApi = get(),
            animeDatabase = get()
        )
    }
}