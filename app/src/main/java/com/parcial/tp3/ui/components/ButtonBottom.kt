package com.parcial.tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parcial.tp3.ui.theme.PrimaryBlue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.parcial.tp3.ui.theme.BackgroundWhite

@Composable
fun ButtonBottom(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    bottomPadding: Dp = 24.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding)
            .height(56.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(PrimaryBlue)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = BackgroundWhite
        )
    }
}
