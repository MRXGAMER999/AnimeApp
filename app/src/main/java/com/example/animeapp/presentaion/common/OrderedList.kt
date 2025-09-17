package com.example.animeapp.presentaion.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.Text
import com.example.animeapp.ui.theme.SMALL_PADDING

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    text: Color
){
    Column{
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = text,
            style = MaterialTheme.typography.headlineMediumEmphasized,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. $item",
                color = text,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderedListPreview(){
    OrderedList(
        title = "Family",
        items = listOf("Minato", "Kushina"),
        text = MaterialTheme.colorScheme.onSurface

    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OrderedListPreviewDark(){
    OrderedList(
        title = "Family",
        items = listOf("Minato", "Kushina"),
        text = MaterialTheme.colorScheme.onSurface

    )
}