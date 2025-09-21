package com.example.animeapp.presentaion.screens.home

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
import com.example.animeapp.ui.theme.getThemeBasedGradient
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToSearch: () -> Unit = { },
               onHeroClick: (Int) -> Unit ){
    val homeViewModel: HomeViewModel = koinViewModel()
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val categories = listOf("Boruto", "Demon Slayer")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = getThemeBasedGradient())
    ){
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeTopBar(scrollBehavior = scrollBehavior,
                    onSearchClick = {
                        onNavigateToSearch()
                    })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                ListContent(heroes = allHeroes){
                    onHeroClick(it)
                }
            }
        }
    }
}





@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(){

    }
}