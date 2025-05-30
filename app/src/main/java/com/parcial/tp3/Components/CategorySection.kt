package com.parcial.tp3.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parcial.tp3.R


@Composable
fun CategoryChip(text: String, selected: Boolean) {
    val backgroundColor = if (selected) Color(0xFF7A5FFF) else Color(0xFFF0F0F0)
    val textColor = if (selected) Color.White else Color.Gray

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor)
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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "View All",
                color = Color(0xFF7A5FFF),
                fontSize = 14.sp
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
                    .background(Color(0xFFF0F0F0)),
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
