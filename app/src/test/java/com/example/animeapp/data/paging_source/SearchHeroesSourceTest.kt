package com.example.animeapp.data.paging_source

import androidx.paging.PagingSource
import com.example.animeapp.data.remote.AnimeApi
import com.example.animeapp.data.remote.FakeApi
import com.example.animeapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchHeroesSourceTest {
    private lateinit var animeApi: AnimeApi
    private lateinit var heroes: List<Hero>

    @Before
    fun setup() {
        animeApi = FakeApi()
        heroes = listOf(
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
    }

    @Test
    fun `Search api with existing hero name, expect single hero result`() =
        runBlocking {
        val heroSource = SearchHeroesSource(animeApi = animeApi, query = "Sasuke")
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
    }

    @Test
    fun `Search api with existing hero name, expect multiple hero result`() =
        runBlocking {
            val heroSource = SearchHeroesSource(animeApi = animeApi, query = "sa")
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty hero name, assert empty heroes list and LoadResult Page`() =
        runBlocking {
            val heroSource = SearchHeroesSource(animeApi = animeApi, query = "")
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = animeApi.searchHeroes("").heroes
            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }


}