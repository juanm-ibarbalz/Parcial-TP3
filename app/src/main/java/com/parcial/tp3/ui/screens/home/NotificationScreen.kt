package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.components.NotificationToggle
import com.parcial.tp3.ui.components.NotificationList
import androidx.navigation.NavHostController


@Composable
fun NotificationScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf("Activity") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        NotificationHeader(navController)
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            NotificationToggle(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }
        Spacer(modifier = Modifier.height(24.dp))
        NotificationList(selectedTab = selectedTab)
    }
}
