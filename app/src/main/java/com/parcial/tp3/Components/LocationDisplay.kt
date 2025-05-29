package com.parcial.tp3.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LocationDisplay() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Location",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Change location",
                tint = Color.Gray
            )
        }

        Text(
            text = "Jebres, Surakarta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}
