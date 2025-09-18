package com.example.animeapp.presentaion.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.ContentAlpha
import com.example.animeapp.R
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.presentaion.common.InfoBox
import com.example.animeapp.presentaion.common.OrderedList
import com.example.animeapp.ui.theme.INFO_ICON_SIZE
import com.example.animeapp.ui.theme.LARGE_PADDING
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.animeapp.util.Constants.ABOUT_TEXT_MAX_LINES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    selectedHero: Hero?,
){
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        },

    ) {

    }

}



@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
){
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(MEDIUM_PADDING)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = "Power",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = "month",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = "Birthday",
                textColor = contentColor
            )

        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            color = contentColor,
            style = MaterialTheme.typography.titleMediumEmphasized,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES)


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = "Family",
                items = selectedHero.family,
                text = contentColor
            )
            OrderedList(
                title = "Abilities",
                items = selectedHero.abilities,
                text = contentColor
            )
            OrderedList(
                title = "Nature Types",
                items = selectedHero.natureTypes,
                text = contentColor
            )
        }

    }

}


@Composable
@Preview
fun BottomSheetContentPreview(){
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "He was a fearless Nigga who wanted to be a ninja." +
                    "He was a fearless Nigga who wanted to be a ninja.",
            rating = 4.5,
            power = 9,
            month = "April",
            day = "1st",
            family = listOf("Nigga", "Nigga2", "Nigga3"),
            abilities = listOf("Niggas Power", "Niggas Power", "Niggas Power"),
            natureTypes = listOf("FireGuns", "WaterMelon")
        )
    )

}