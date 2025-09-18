package com.example.animeapp.presentaion.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.ContentAlpha
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.animeapp.R
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.presentaion.common.InfoBox
import com.example.animeapp.presentaion.common.OrderedList
import com.example.animeapp.ui.theme.INFO_ICON_SIZE
import com.example.animeapp.ui.theme.LARGE_PADDING
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.animeapp.util.Constants.ABOUT_TEXT_MAX_LINES
import com.example.animeapp.util.Constants.BASE_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    selectedHero: Hero?,
    onCloseClicked: () -> Unit
){
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetDragHandle = null,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    onCloseClicked = {
                        onCloseClicked()
                    })
            }
        }

    )

}




@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
){
    val imageUrl = "$BASE_URL$heroImage"
    val placeholderRes = if (isSystemInDarkTheme()) {
        R.drawable.placeholder_dark
    } else {
        R.drawable.placeholder_light
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(300)
                .build()
            ,
            contentDescription = heroImage,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = placeholderRes),
            error = painterResource(id = placeholderRes)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = MEDIUM_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White)
            }
        }
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