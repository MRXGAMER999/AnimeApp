package com.example.animeapp.presentaion.common


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.wear.compose.material.ContentAlpha
import com.example.animeapp.R
import com.example.animeapp.domain.model.Hero
import com.example.animeapp.ui.theme.MEDIUM_PADDING
import com.example.animeapp.ui.theme.NETWORK_PLACEHOLDER_HEIGHT
import java.net.ConnectException
import java.net.SocketTimeoutException

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null){
    var message by remember{
        mutableStateOf("Find your Favourite Hero!")
    }
    var icon by remember{
        mutableStateOf(R.drawable.search_hero)
    }
    if (error!= null){
        message = parseErrorMessage(error)
        icon = R.drawable.network_error
    }
    var startAnimation by remember{ mutableStateOf(false)}
    val alphaAnim by animateFloatAsState(
        targetValue = if(startAnimation) ContentAlpha.high else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
    }
    EmptyContent(alphaAnim = alphaAnim, icon = icon, message = message, heroes = heroes, error = error)
}
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    heroes: LazyPagingItems<Hero>? = null,
    error: LoadState.Error? = null){

    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    
    LaunchedEffect(heroes?.loadState?.refresh) {
        isRefreshing = heroes?.loadState?.refresh is LoadState.Loading
    }
    
    if (error != null) {
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = isRefreshing,
            onRefresh = {
                heroes?.refresh()
            },

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    modifier = Modifier.size(NETWORK_PLACEHOLDER_HEIGHT)
                        .padding(MEDIUM_PADDING)
                        .alpha(alphaAnim),
                    painter = painterResource(id = icon),
                    contentDescription = message,
                    tint = MaterialTheme.colorScheme.onBackground)

                Text(modifier = Modifier
                    .padding(MEDIUM_PADDING)
                    .alpha(alphaAnim),
                    text = message,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMediumEmphasized,
                    fontWeight = MaterialTheme.typography.headlineMediumEmphasized.fontWeight,
                    fontSize = MaterialTheme.typography.headlineMediumEmphasized.fontSize
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                modifier = Modifier.size(NETWORK_PLACEHOLDER_HEIGHT)
                    .padding(MEDIUM_PADDING)
                    .alpha(alphaAnim),
                painter = painterResource(id = icon),
                contentDescription = message,
                tint = MaterialTheme.colorScheme.onBackground)

            Text(modifier = Modifier
                .padding(MEDIUM_PADDING)
                .alpha(alphaAnim),
                text = message,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMediumEmphasized,
                fontWeight = MaterialTheme.typography.headlineMediumEmphasized.fontWeight,
                fontSize = MaterialTheme.typography.headlineMediumEmphasized.fontSize
            )
        }
    }

}


fun parseErrorMessage(error: LoadState.Error): String{
    return when(error.error){
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}

