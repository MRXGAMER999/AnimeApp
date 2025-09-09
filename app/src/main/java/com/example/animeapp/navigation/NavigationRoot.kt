package com.example.animeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
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

                    }
                }
                is WelcomeScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {

                    }
                }
                is HomeScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {

                    }
                }
                is DetailsScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {

                    }
                }
                is SearchScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {

                    }
                }
                else -> error("Unknown key: $key")
            }
        }
    )
}