package com.example.animeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.animeapp.presentaion.screens.details.DetailsScreen
import com.example.animeapp.presentaion.screens.home.HomeScreen
import com.example.animeapp.presentaion.screens.search.SearchScreen
import com.example.animeapp.presentaion.screens.splash.SplashScreen
import com.example.animeapp.presentaion.screens.welcome.WelcomeScreen
import kotlinx.serialization.Serializable

@Serializable
object SplashScreenKey: NavKey

@Serializable
object WelcomeScreenKey: NavKey

@Serializable
object HomeScreenKey: NavKey

@Serializable
data class DetailsScreenKey(val heroId: Int): NavKey

@Serializable
object SearchScreenKey: NavKey


@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(SplashScreenKey)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = { key ->
            when(key){
                is SplashScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        SplashScreen(
                            onNavigateToWelcome = {
                                backStack.remove(SplashScreenKey)
                                backStack.add(WelcomeScreenKey)
                            },
                            onNavigateToHome = {
                                backStack.remove(SplashScreenKey)
                                backStack.add(HomeScreenKey)
                            }
                        )
                    }
                }
                is WelcomeScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        WelcomeScreen {
                            backStack.remove(WelcomeScreenKey)
                            backStack.add(HomeScreenKey)
                        }
                    }
                }
                is HomeScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        HomeScreen(
                            onNavigateToSearch = {
                                backStack.add(SearchScreenKey)
                            },
                            onHeroClick = { heroId: Int ->
                                backStack.add(DetailsScreenKey(heroId))
                            }
                        )
                    }
                }
                is DetailsScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        DetailsScreen(
                            heroId = key.heroId,
                            onNavigateBack = {
                                backStack.remove(key)
                            }
                        )
                    }
                }
                is SearchScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        SearchScreen(
                            onBackToHomeScreen = {
                                backStack.remove(SearchScreenKey)
                                backStack.add(HomeScreenKey)
                            },
                            onHeroClick = { heroId: Int ->
                                backStack.remove(SearchScreenKey)
                                backStack.add(DetailsScreenKey(heroId))
                            }
                        )
                    }
                }
                else -> error("Unknown key: $key")
            }
        }
    )
}
