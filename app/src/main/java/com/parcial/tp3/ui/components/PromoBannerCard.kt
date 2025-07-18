package com.parcial.tp3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parcial.tp3.R
import com.parcial.tp3.ui.theme.BackgroundWhite

@Composable
fun PromoBannerCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {

        Image(
            painter = painterResource(id = R.drawable.imagen_banner),
            contentDescription = "Promo Background",
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(20.dp))
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 60.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Royal Canin\nAdult Pomeraniann",
                style = MaterialTheme.typography.labelMedium,
                color = BackgroundWhite,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Get an interesting promo\nhere, without conditions",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 18.sp
            )
        }
    }
}
