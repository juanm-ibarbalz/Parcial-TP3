package com.parcial.tp3.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.theme.Poppins
import androidx.compose.ui.text.font.FontWeight

@Composable
fun NotificationSettingsScreen(navController: NavHostController) {
    var likedPost by remember { mutableStateOf(true) }
    var newMessage by remember { mutableStateOf(true) }
    var itemSold by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        NotificationHeader(
            navController = navController,
            title = "Notification"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // SOCIAL
        Text(
            text = "Social",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        NotificationItem("Liked Post", likedPost) { likedPost = it }
        NotificationItem("New Message", newMessage) { newMessage = it }

        Spacer(modifier = Modifier.height(24.dp))

        // STORE
        Text(
            text = "Store",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        NotificationItem("Item Sold", itemSold) { itemSold = it }
    }
}

@Composable
fun NotificationItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = Poppins,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF7754F6)
            )
        )
    }
}
