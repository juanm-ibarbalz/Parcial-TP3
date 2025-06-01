package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.components.NotificationList
import com.parcial.tp3.ui.components.NotificationToggle

@Composable
fun NotificationScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf("Activity") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        NotificationHeader(navController = navController, title = "Notification")

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            NotificationToggle(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            NotificationList(selectedTab = selectedTab)
        }
    }
}
