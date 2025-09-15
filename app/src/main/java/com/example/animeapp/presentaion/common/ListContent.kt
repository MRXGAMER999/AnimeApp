package com.example.animeapp.presentaion.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.animeapp.R
import androidx.paging.compose.itemKey
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.presentaion.componenets.ShimmerEffect
import com.example.animeapp.presentaion.componenets.SimpleRatingWidget
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.animeapp.ui.theme.HERO_ITEM_HEIGHT
import com.example.animeapp.ui.theme.LARGE_PADDING
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.SMALL_PADDING
import com.example.animeapp.util.Constants.BASE_URL




@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>
) {
    Log.d("ListContent", heroes.loadState.toString())
    val result = handlePagingResult(heroes = heroes)
    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),

            ) {
            items(
                count = heroes.itemCount,
                key = heroes.itemKey { it.id }
            ) { index ->
                heroes[index]?.let { hero ->
                    HeroItem(hero = hero)
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
): Boolean{
    heroes.apply {
        val error = when{
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when{
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error)
                false
            }
            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HeroItem(hero: Hero){
    val isDarkTheme = isSystemInDarkTheme()
    val placeholderRes = if (isDarkTheme) {
        R.drawable.placeholder_dark
    } else {
        R.drawable.placeholder_light
    }
    
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {

            },
        contentAlignment = Alignment.BottomStart
    ){
        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_URL${hero.image}")
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(300)
                    .build()
                ,
                contentDescription = hero.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = placeholderRes),
                error = painterResource(id = placeholderRes)
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceContainerLow.copy(0.5f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMediumEmphasized,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMediumEmphasized,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SimpleRatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HeroItemPreview() {
    AnimeAppTheme {
        HeroItem(
            hero = Hero(
                id = 1,
                name = "Naruto Uzumaki",
                image = "/images/naruto.jpg",
                about = "Naruto Uzumaki is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood.",
                rating = 4.9,
                power = 98,
                month = "October",
                day = "10th",
                family = listOf("Minato", "Kushina", "Boruto", "Himawari", "Hinata"),
                abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
                natureTypes = listOf("Wind", "Lightning", "Earth", "Water", "Fire")
            )
        )
    }
}

@Preview(showBackground = true, name = "Multiple Hero Items")
@Composable
fun MultipleHeroItemsPreview() {
    AnimeAppTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(3) { index ->
                val heroes = listOf(
                    Hero(
                        id = 1,
                        name = "Naruto Uzumaki",
                        image = "/images/naruto.jpg",
                        about = "Main protagonist of Naruto series",
                        rating = 4.9,
                        power = 98,
                        month = "October",
                        day = "10th",
                        family = listOf("Minato", "Kushina"),
                        abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
                        natureTypes = listOf("Wind", "Lightning")
                    ),
                    Hero(
                        id = 2,
                        name = "Sasuke Uchiha",
                        image = "/images/sasuke.jpg",
                        about = "Last surviving member of the Uchiha clan",
                        rating = 4.8,
                        power = 97,
                        month = "July",
                        day = "23rd",
                        family = listOf("Itachi", "Fugaku", "Mikoto"),
                        abilities = listOf("Sharingan", "Rinnegan", "Chidori"),
                        natureTypes = listOf("Lightning", "Fire")
                    ),
                    Hero(
                        id = 3,
                        name = "Sakura Haruno",
                        image = "/images/sakura.jpg",
                        about = "Medical ninja and member of Team 7",
                        rating = 4.5,
                        power = 89,
                        month = "March",
                        day = "28th",
                        family = listOf("Kizashi", "Mebuki", "Sarada"),
                        abilities = listOf("Medical Ninjutsu", "Super Strength", "Healing"),
                        natureTypes = listOf("Earth", "Water")
                    )
                )
                HeroItem(hero = heroes[index])
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview() {
    AnimeAppTheme {
        HeroItem(
            hero = Hero(
                id = 1,
                name = "Naruto Uzumaki",
                image = "/images/naruto.jpg",
                about = "Naruto Uzumaki is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood.",
                rating = 4.9,
                power = 98,
                month = "October",
                day = "10th",
                family = listOf("Minato", "Kushina", "Boruto", "Himawari", "Hinata"),
                abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
                natureTypes = listOf("Wind", "Lightning", "Earth", "Water", "Fire")
            )
        )
    }
}

