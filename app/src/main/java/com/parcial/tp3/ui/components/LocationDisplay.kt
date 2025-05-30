package com.parcial.tp3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.parcial.tp3.R
import com.parcial.tp3.ui.theme.MediumGrey
import com.parcial.tp3.ui.theme.PureBlack

@Composable
fun LocationDisplay() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Location",
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Change location",
                tint = MediumGrey
            )
        }

        Text(
            text = "Jebres, Surakarta",
            style = MaterialTheme.typography.bodyLarge,
            color = PureBlack
        )
    }
}
