package com.parcial.tp3.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.R

@Composable
fun ChangeEmailScreen(
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(24.dp)
    ) {
        NotificationHeader(navController = navController, title = "Change Email")

        Spacer(modifier = Modifier.height(24.dp))

        Text("New Email", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Abdul", color = Color.LightGray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7754F6),
                contentColor = Color.White
            ),
            shape = CircleShape
        ) {
            Text("Save", fontWeight = FontWeight.Bold)
        }
    }
}
