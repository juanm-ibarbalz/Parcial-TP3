package com.parcial.tp3.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parcial.tp3.R
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.theme.Poppins

@Composable
fun AccountScreen(
    navController: NavHostController,
    onSaveClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("Abdul") }
    var username by remember { mutableStateOf("Abdul") }
    var email by remember { mutableStateOf("Abdul") }

    val inputColors = OutlinedTextFieldDefaults.colors(
        focusedLabelColor = Color.Gray,
        unfocusedLabelColor = Color.Gray,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        NotificationHeader(navController = navController, title = "Account")

        Spacer(modifier = Modifier.height(8.dp)) // alineación visual con SettingsDetailScreen

        // Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ilustration_profile_banner_grey),
                contentDescription = "Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_account_edit),
                    contentDescription = "Edit Banner",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Avatar con botón superior derecho
        Box(
            modifier = Modifier
                .offset(y = (-40).dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(modifier = Modifier.size(80.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ilustration_pfp_2),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_account_edit),
                        contentDescription = "Edit Avatar",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Abduldul",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input fields
        Text("Name", fontFamily = Poppins, fontSize = 14.sp)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Abdul", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = inputColors
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Username", fontFamily = Poppins, fontSize = 14.sp)
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("Abdul", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = inputColors
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Email", fontFamily = Poppins, fontSize = 14.sp)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Abdul", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = inputColors
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7754F6),
                contentColor = Color.White
            )
        ) {
            Text("Save Changes")
        }
    }
}
