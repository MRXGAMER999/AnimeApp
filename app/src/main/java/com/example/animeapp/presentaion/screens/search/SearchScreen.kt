package com.example.animeapp.presentaion.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.presentaion.common.ListContent
import org.koin.androidx.compose.koinViewModel
import com.example.animeapp.ui.theme.getThemeBasedGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackToHomeScreen: () -> Unit = { },
    onHeroClick: (Int) -> Unit = { },
    viewModel: SearchViewModel = koinViewModel()
){
    val heroes = viewModel.searchedHeroes.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Box(
        modifier = Modifier
        .fillMaxSize()
        .background(brush = getThemeBasedGradient())
    )  {
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SearchTopBar(
                    searchQuery = viewModel.searchQuery.value,
                    onSearchQueryChange = viewModel::updateSearchQuery,
                    onClosed = {
                        viewModel.clearSearchQuery()
                        viewModel.clearSearchedHeroes()
                        onBackToHomeScreen()
                    },
                    onSearched = {
                        viewModel.searchHeroes(query = viewModel.searchQuery.value)
                    }
                )
            }

        ){ paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                ListContent(heroes = heroes){
                    onHeroClick(it)
                }
            }
        }

    }
}

@Preview
@Composable
fun SearchScreenPreviewLight(){
    SearchScreen(onHeroClick = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreviewDark(){
    SearchScreen(onHeroClick = {})
}