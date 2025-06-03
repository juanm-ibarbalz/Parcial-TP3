package com.parcial.tp3.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parcial.tp3.R
import com.parcial.tp3.ui.components.NotificationHeader

@Composable
fun SecurityScreen(
    navController: NavHostController,
    onChangePasswordClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(16.dp)
    ) {
        NotificationHeader(navController = navController, title = "Security")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Security",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SecurityOption(label = "Change Password", onClick = onChangePasswordClick)
        Spacer(modifier = Modifier.height(12.dp))
        SecurityOption(label = "Change Email", onClick = onChangeEmailClick)
    }
}

@Composable
fun SecurityOption(label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFFF4F4F4), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings_security),
                contentDescription = label,
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = label, modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Arrow",
            tint = Color.Black
        )
    }
}
