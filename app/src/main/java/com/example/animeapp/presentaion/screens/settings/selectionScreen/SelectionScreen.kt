package com.example.animeapp.presentaion.screens.settings.selectionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.presentaion.screens.settings.SettingItem
import com.example.animeapp.presentaion.screens.settings.SettingsTopBar
import com.example.animeapp.presentaion.screens.settings.SettingsViewModel
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.getThemeBasedGradient
import org.koin.androidx.compose.koinViewModel
import com.example.animeapp.util.Constants


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectionScreen(
    settingsViewModel: SettingsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit = {}
) {
    val selectedCategories by settingsViewModel.selectedCategories.collectAsState()
    
    val availableCategories = Constants.AVAILABLE_CATEGORIES

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getThemeBasedGradient())
    ){
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                SettingsTopBar(
                    onClosed = onNavigateBack
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(MEDIUM_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingItem(
                    icon = Icons.Default.Category,
                    title = "Anime Category",
                    subtitle = "Please select one or multiple Anime categories"
                )

                FlowRow(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    availableCategories.forEach { category ->
                        InputChip(
                            onClick = {
                                settingsViewModel.toggleCategory(category)
                            },
                            selected = selectedCategories.contains(category),
                            label = {
                                Text(category)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelectionScreenPreview() {
    SelectionScreen()
}