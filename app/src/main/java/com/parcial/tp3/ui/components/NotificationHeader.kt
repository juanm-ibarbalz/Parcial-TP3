package com.parcial.tp3.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.parcial.tp3.R
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*


@Composable
fun NotificationHeader(navController: NavHostController) {
    Column {
        Spacer(modifier = Modifier.height(48.dp)) // igual que TopIcons

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart)
                    .shadow(6.dp, RoundedCornerShape(16.dp))
            ) {
                RoundedIconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Back"
                    )
                }
            }

            Text(
                text = "Notification",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}