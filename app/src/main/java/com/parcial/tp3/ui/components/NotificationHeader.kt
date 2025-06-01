package com.parcial.tp3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.parcial.tp3.R
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.WindowInsets
import com.parcial.tp3.ui.theme.PureBlack

@Composable
fun NotificationHeader(
    navController: NavHostController,
    title: String = "Notification",
    showFavoriteIcon: Boolean = false
) {
    Column {
        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart)
            ) {
                RoundedIconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Back"
                    )
                }
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = PureBlack,
                modifier = Modifier.align(Alignment.Center)
            )

            if (showFavoriteIcon) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    RoundedIconButton(onClick = { /* TODO: marcar como favorito */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = "Favorite"
                        )
                    }
                }
            }
        }
    }
}
