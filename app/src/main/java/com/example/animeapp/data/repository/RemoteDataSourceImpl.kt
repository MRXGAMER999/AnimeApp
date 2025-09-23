package com.example.animeapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.data.paging_source.HeroRemoteMediator
import com.example.animeapp.data.paging_source.SearchHeroesSource
import com.example.animeapp.data.remote.AnimeApi
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.domain.repository.RemoteDataSource
import com.example.animeapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val animeApi: AnimeApi,
    private val animeDatabase: AnimeDatabase

): RemoteDataSource {
    private val heroDao = animeDatabase.heroDao()
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHeroes(category: Set<String>): Flow<PagingData<Hero>> {
        val categoriesOrNull: Set<String>? = if (category.isEmpty()) null else category
        val pagingSourceFactory = {
            when {
                category.isEmpty() -> heroDao.getAllHeroes()
                category.size == 1 -> heroDao.getAllHeroesByCategory(category.first())
                else -> heroDao.getAllHeroesByCategories(category.toList())
            }
        }
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                initialLoadSize = ITEMS_PER_PAGE * 3,
                prefetchDistance = ITEMS_PER_PAGE * 2,
                enablePlaceholders = false,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED
            ),
            remoteMediator = HeroRemoteMediator(
                animeApi = animeApi,
                animeDatabase = animeDatabase,
                categories = categoriesOrNull
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(animeApi = animeApi, query = query)
            }
        ).flow
    }
}