package com.parcial.tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.parcial.tp3.R
import com.parcial.tp3.ui.theme.BackgroundWhite
import com.parcial.tp3.ui.theme.PureBlack


@Composable
fun TopIcons(
    navController: NavController,
    onSearchClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
        RoundedIconButton(onClick = onSearchClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = PureBlack
            )
        }

        RoundedIconButton(onClick = {
            navController.navigate("notifications")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notifications",
                tint = PureBlack
            )
        }
    }
}

@Composable
fun RoundedIconButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundWhite)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
