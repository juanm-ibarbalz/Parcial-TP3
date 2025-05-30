package com.parcial.tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.parcial.tp3.R
import com.parcial.tp3.ui.theme.LightSurfaceGrey
import com.parcial.tp3.ui.theme.MediumGrey
import com.parcial.tp3.ui.theme.PrimaryBlue


@Composable
fun CategoryChip(text: String, selected: Boolean) {
    val backgroundColor = if (selected) PrimaryBlue else LightSurfaceGrey
    val textColor = if (selected) Color.White else MediumGrey

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun CategorySection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "View All",
                color = PrimaryBlue,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightSurfaceGrey),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_swap),
                    contentDescription = "Filter"
                )
            }

            CategoryChip(text = "Food", selected = true)
            CategoryChip(text = "Toys", selected = false)
            CategoryChip(text = "Accessories", selected = false)
        }
    }
}
