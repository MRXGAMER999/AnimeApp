package com.example.animeapp.presentaion.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.animeapp.R
import com.example.animeapp.domain.model.OnBoardingPage
import com.example.animeapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.getThemeBasedGradient
import com.example.animeapp.ui.theme.getThemeBasedButtonColors
import com.example.animeapp.util.Constants.LAST_ON_BOARDING_PAGE
import com.example.animeapp.util.Constants.ON_BOARDING_PAGE_COUNT

@Composable
fun WelcomeScreen(){
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState(initialPage = 0) { ON_BOARDING_PAGE_COUNT }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getThemeBasedGradient()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        Spacer(modifier = Modifier.height(60.dp))
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.Black else Color.White
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(20.dp)
                )
            }
        }
         FinishButton(
             modifier = Modifier.height(80.dp), // Fixed height to prevent UI shifting
             pagerState = pagerState
         ) {

         }

    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.onboarding_image)
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = MEDIUM_PADDING),
            text = onBoardingPage.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = MEDIUM_PADDING),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

    }
}




@Composable
 fun FinishButton(
     modifier: Modifier,
     pagerState : PagerState,
     onClick: () -> Unit
 ){
     Box(
         modifier = modifier.fillMaxWidth(),
         contentAlignment = Alignment.Center
     ) {
         AnimatedVisibility(
             modifier = Modifier.fillMaxWidth(),
             visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE
         ) {
             Button(
                 onClick = onClick,
                 colors = getThemeBasedButtonColors(),
                 modifier = Modifier.padding(horizontal = EXTRA_LARGE_PADDING)
             ) {
                 Text(
                     text = "Finish",
                     fontWeight = FontWeight.Bold,
                     style = MaterialTheme.typography.bodyLarge
                 )
             }
        }
     }
 }










// Preview functions for each onboarding page
@Preview(showSystemUi = true)
@Composable
fun FirstPagePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getThemeBasedGradient()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }

}

@Preview(showSystemUi = true)
@Composable
fun SecondPagePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getThemeBasedGradient()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Preview(showSystemUi = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThirdPagePreview() {
    AnimeAppTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(getThemeBasedGradient()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagerScreen(onBoardingPage = OnBoardingPage.Third)
        }
    }
}

