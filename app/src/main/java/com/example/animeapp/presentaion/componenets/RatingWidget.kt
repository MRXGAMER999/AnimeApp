package com.example.animeapp.presentaion.componenets
import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.animation.core.animateDpAsState
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.drawscope.clipRect


@SuppressLint("DefaultLocale")
@Composable
fun RatingWidget(
    modifier: Modifier = Modifier,
    rating: Float,
    maxRating: Int = 5,
    showRatingText: Boolean = true
) {
    val animatedElevation by animateDpAsState(
        targetValue = if (rating > 0f) 8.dp else 2.dp,
        label = "elevation"
    )

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(maxRating) { index ->
                    val starValue = index + 1
                    val starState = when {
                        rating >= starValue -> StarState.FULL
                        rating >= starValue - 0.5f -> StarState.HALF
                        else -> StarState.EMPTY
                    }

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = when (starState) {
                                    StarState.FULL -> MaterialTheme.colorScheme.primary
                                    StarState.HALF -> MaterialTheme.colorScheme.primaryContainer
                                    StarState.EMPTY -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                                },
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        when (starState) {
                            StarState.FULL -> {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Full star",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            StarState.HALF -> {

                                Box {
                                    Icon(
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = "Half star background",
                                        tint = MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "Half star fill",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clipToBounds()
                                            .drawWithContent {
                                                clipRect(right = size.width / 2) {
                                                    this@drawWithContent.drawContent()
                                                }
                                            }
                                    )
                                }
                            }
                            StarState.EMPTY -> {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = "Empty star",
                                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (showRatingText) {
                Text(
                    text = String.format("%.1f / 5.0", rating),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

enum class StarState {
    EMPTY,
    HALF,
    FULL
}

// Simple star rating without card (alternative)
@SuppressLint("DefaultLocale")
@Composable
fun SimpleRatingWidget(
    modifier: Modifier = Modifier,
    rating: Float,
    maxRating: Int = 5,
    starSize: Dp = 24.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxRating) { index ->
            val starValue = index + 1
            when {
                rating >= starValue -> {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Full star",
                        tint = Color(0xFFFFD700), // Gold color
                        modifier = Modifier.size(starSize)
                    )
                }
                rating >= starValue - 0.5f -> {
                    Box {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Half star background",
                            tint = Color.Gray,
                            modifier = Modifier.size(starSize)
                        )
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Half star fill",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier
                                .size(starSize)
                                .clip(
                                    GenericShape { size, _ ->
                                        addRect(
                                            Rect(
                                                offset = Offset.Zero,
                                                size = Size(size.width / 2, size.height)
                                            )
                                        )
                                    }
                                )
                        )
                    }
                }
                else -> {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Empty star",
                        tint = Color.Gray,
                        modifier = Modifier.size(starSize)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = String.format("%.1f", rating),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun ApiCardRatingPreview() {
    MaterialTheme(
        colorScheme = dynamicLightColorScheme(LocalContext.current)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Product Ratings from API",
                style = MaterialTheme.typography.headlineSmall
            )

            // Example API ratings
            RatingWidget(
                rating = 4.5f,
                modifier = Modifier.fillMaxWidth()
            )

            Text("Other rating examples:")

            // Different rating values
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SimpleRatingWidget(rating = 1.0f)
                SimpleRatingWidget(rating = 2.5f)
                SimpleRatingWidget(rating = 3.7f)
                SimpleRatingWidget(rating = 4.2f)
                SimpleRatingWidget(rating = 5.0f)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true, name = "Different API Ratings")
@Composable
fun ApiRatingStatesPreview() {
    MaterialTheme(
        colorScheme = dynamicLightColorScheme(LocalContext.current)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "API Rating Examples:",
                style = MaterialTheme.typography.headlineSmall
            )

            Text("Perfect Rating (5.0):", style = MaterialTheme.typography.bodyMedium)
            RatingWidget(rating = 5.0f)

            Text("High Rating (4.3):", style = MaterialTheme.typography.bodyMedium)
            RatingWidget(rating = 4.3f)

            Text("Average Rating (3.5):", style = MaterialTheme.typography.bodyMedium)
            RatingWidget(rating = 3.5f)

            Text("Low Rating (2.1):", style = MaterialTheme.typography.bodyMedium)
            RatingWidget(rating = 2.1f)

            Text("Poor Rating (1.2):", style = MaterialTheme.typography.bodyMedium)
            RatingWidget(rating = 1.2f)

            Text("No Rating (0.0):", style = MaterialTheme.typography.bodyMedium)
            RatingWidget(rating = 0.0f)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ApiCardRatingDarkPreview() {
    MaterialTheme(
        colorScheme = dynamicDarkColorScheme(LocalContext.current)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "API Rating - Dark Theme",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            RatingWidget(
                rating = 4.7f,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Compact versions:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            SimpleRatingWidget(rating = 3.8f)
            SimpleRatingWidget(rating = 4.5f)
        }
    }
}
