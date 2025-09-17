package com.example.animeapp.di

import androidx.room.Room
import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.data.repository.LocalDataSourceImpl
import com.example.animeapp.domain.repository.LocalDataSource
import com.example.animeapp.util.Constants.ANIME_DATABASE
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java,
            ANIME_DATABASE
        ).build()
    }


        single<LocalDataSource>{
            LocalDataSourceImpl(
                get()
            )
        }


}