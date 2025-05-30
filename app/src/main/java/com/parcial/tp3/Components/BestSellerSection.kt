package com.parcial.tp3.Components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parcial.tp3.R


@Composable
fun BestSellerSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Best Seller",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "View All",
                fontSize = 14.sp,
                color = Color(0xFF7A5FFF)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProductCard(
                name = "RC Kitten",
                price = "20,99",
                imageRes = R.drawable.comida_card,
                onAddClick = { /* TODO */ }
            )
            ProductCard(
                name = "RC Persian",
                price = "22,99",
                imageRes = R.drawable.comida_card,
                onAddClick = { /* TODO */ }
            )
        }

    }
}
