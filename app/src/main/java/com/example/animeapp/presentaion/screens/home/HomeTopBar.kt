package com.example.animeapp.presentaion.screens.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animeapp.ui.theme.getThemeBasedTopAppBarColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        colors = getThemeBasedTopAppBarColors(),
        title = {
            Text(
                text = "Explore",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
    )
}