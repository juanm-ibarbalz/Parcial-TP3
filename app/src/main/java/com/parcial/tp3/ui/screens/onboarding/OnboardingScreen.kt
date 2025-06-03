package com.parcial.tp3.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import com.parcial.tp3.navigation.Screen

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Meet your animal needs here",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.onboarding_image),
            contentDescription = "Onboarding illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Text(
            text = "Get interesting promos here, register your account immediately so you can meet your animal needs.",
            fontSize = 14.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.Gray
        )

        // Page indicator (hardcoded for now)
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(8.dp)
                .background(Color.Gray, RoundedCornerShape(4.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier
                .size(8.dp)
                .background(Color.LightGray, RoundedCornerShape(4.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier
                .size(8.dp)
                .background(Color.LightGray, RoundedCornerShape(4.dp)))
        }

        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D43FF)),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text("Get Started", color = Color.White)
        }
    }
}
