package com.example.animeapp.presentaion.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.wear.compose.material.ContentAlpha
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.animeapp.R
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.presentaion.common.InfoBox
import com.example.animeapp.presentaion.common.OrderedList
import com.example.animeapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.example.animeapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.animeapp.ui.theme.INFO_ICON_SIZE
import com.example.animeapp.ui.theme.LARGE_PADDING
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.animeapp.util.Constants.ABOUT_TEXT_MAX_LINES
import com.example.animeapp.util.Constants.BASE_URL
import com.example.animeapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    selectedHero: Hero?,
    onCloseClicked: () -> Unit,
    colors: Map<String, String>,
){
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedHero) {
        vibrant = colors["vibrant"] ?: "#000000"
        darkVibrant = colors["darkVibrant"] ?: "#000000"
        onDarkVibrant = colors["onDarkVibrant"] ?: "#ffffff"
    }





    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        )
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction()

    val radiusAnim by animateDpAsState(
        targetValue = lerp(EXPANDED_RADIUS_LEVEL, EXTRA_LARGE_PADDING, currentSheetFraction),
        label = "Radius Animation"
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetDragHandle = null,
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                    ) }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
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
    // Map sheet fraction [0 (expanded), 1 (hidden)] to image height fraction [MIN, 1]
    val imageHeightFraction = (
        MIN_BACKGROUND_IMAGE_HEIGHT + (1f - MIN_BACKGROUND_IMAGE_HEIGHT) * imageFraction
    ).coerceIn(MIN_BACKGROUND_IMAGE_HEIGHT, 1f)
    val placeholderRes = if (isSystemInDarkTheme()) {
        R.drawable.placeholder_dark
    } else {
        R.drawable.placeholder_light
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageHeightFraction)
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
            modifier = Modifier.fillMaxWidth()
                .padding(top = MEDIUM_PADDING),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldState.currentSheetFraction(): Float {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val peekHeightPx = with(density) { MIN_SHEET_HEIGHT.toPx() }
    val maxOffset = (screenHeightPx - peekHeightPx).coerceAtLeast(1f)

    val initial = when (bottomSheetState.currentValue) {
        SheetValue.Expanded -> 0f
        SheetValue.Hidden -> 1f
        else -> 0f
    }
    val fractionState = remember { mutableFloatStateOf(initial) }

    LaunchedEffect(bottomSheetState, maxOffset) {
        snapshotFlow { runCatching { bottomSheetState.requireOffset() }.getOrNull() }
            .filterNotNull()
            .map { (it / maxOffset).coerceIn(0f, 1f) }
            .distinctUntilChanged()
            .collect { fractionState.floatValue = it }
    }

    return fractionState.floatValue
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
            about = "He was a fearless ninja who wanted to be stronger." +
                    "He was a fearless ninja who wanted to be stronger.",
            rating = 4.5,
            power = 9,
            month = "April",
            day = "1st",
            family = listOf("Itachi", "Fugaku", "Mikoto"),
            abilities = listOf("Sharingan", "Chidori", "Fire Style"),
            natureTypes = listOf("Fire", "Lightning"),
            category = "Boruto"
        )
    )

}