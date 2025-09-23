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
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.getThemeBasedGradient
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit = { },
    onNavigateToSelection: () -> Unit = { }
){
    val selectedCategories by settingsViewModel.selectedCategories.collectAsState()

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
                .padding(MEDIUM_PADDING),
        )   {
            SettingItem(
                icon = Icons.Default.DarkMode,
                title = "Appearance",
                subtitle = "App's Theme"
            )



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToSelection() }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Multiple Categories",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Multiple Categories",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Select multiple anime categories (${selectedCategories.size} selected)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

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

                SettingItem(
                    icon = Icons.Default.Info,
                    title = "About",
                    subtitle = "About this app"
                )

                }
            }
        }
}
