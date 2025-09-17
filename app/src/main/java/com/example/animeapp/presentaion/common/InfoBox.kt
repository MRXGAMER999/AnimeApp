package com.example.animeapp.presentaion.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.ContentAlpha
import com.example.animeapp.R
import com.example.animeapp.ui.theme.INFO_ICON_SIZE
import com.example.animeapp.ui.theme.SMALL_PADDING

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun InfoBox(
    icon: ImageVector,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_ICON_SIZE),
            imageVector = icon,
            contentDescription = "Info Box Icon",
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                color = textColor,
                style = MaterialTheme.typography.headlineMediumEmphasized,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Black
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview(){
    InfoBox(
        icon = ImageVector.vectorResource(id = R.drawable.ic_bolt),
        iconColor = MaterialTheme.colorScheme.primary,
        bigText = "92",
        smallText = "Power",
        textColor = MaterialTheme.colorScheme.onSurface
    )
}