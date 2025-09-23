package com.example.animeapp.presentaion.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.ui.theme.getThemeBasedGradient
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit = { }
){
    val selectedCategory by settingsViewModel.selectedCategory.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = getThemeBasedGradient())
    ) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            SettingsTopBar(
                onClosed = {
                    onNavigateBack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp),
        )   {
            SettingItem(
                icon = Icons.Default.DarkMode,
                title = "Appearance",
                subtitle = "App's Theme"
            )
            CategorySwitchItem(
                icon = Icons.Default.Category,
                title = "Anime Category",
                subtitle = "Switch between Boruto and Demon Slayer",
                selectedCategory = selectedCategory,
                onCategoryChange = { category ->
                    settingsViewModel.selectCategory(category)
                }
            )
            SettingItem(
                icon = Icons.Default.Info,
                title = "About",
                subtitle = "About this app"
            )

            }
        }
    }
}





@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val selectedCategoryState = remember { mutableStateOf("Boruto") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = getThemeBasedGradient())
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                SettingsTopBar()
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp),
            )   {
                SettingItem(
                    icon = Icons.Default.DarkMode,
                    title = "Appearance",
                    subtitle = "App's Theme"
                )
                CategorySwitchItem(
                    icon = Icons.Default.Category,
                    title = "Anime Category",
                    subtitle = "Switch between Boruto and Demon Slayer",
                    selectedCategory = selectedCategoryState.value,
                    onCategoryChange = { category ->
                        selectedCategoryState.value = category
                    }
                )
                SettingItem(
                    icon = Icons.Default.Info,
                    title = "About",
                    subtitle = "About this app"
                )

                }
            }
        }
}

