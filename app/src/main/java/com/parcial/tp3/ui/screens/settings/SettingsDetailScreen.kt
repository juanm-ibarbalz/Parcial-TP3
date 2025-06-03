package com.parcial.tp3.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parcial.tp3.R
import com.parcial.tp3.ui.components.NotificationHeader

private val poppinsBold = FontFamily(Font(R.font.poppins_bold))
private val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

@Composable
fun SettingsDetailScreen(
    navController: NavHostController,
    onLogoutClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onFaqClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        NotificationHeader(navController = navController, title = "Settings Page")

        LazyColumn {
            item { SectionTitle("Account") }
            item {
                SettingsItem("Account", R.drawable.ic_settings_profile, onClick = onAccountClick)
            }
            item {
                SettingsItem("Address", R.drawable.ic_settings_home)
            }
            item {
                SettingsItem("Notification", R.drawable.ic_settings_notification, onClick = onNotificationClick)
            }
            item {
                SettingsItem("Payment Method", R.drawable.ic_settings_wallet)
            }
            item {
                SettingsItem("Privacy", R.drawable.ic_info_square, onClick = onPrivacyClick)
            }
            item {
                SettingsItem("Security", R.drawable.ic_settings_security, onClick = onSecurityClick)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item { SectionTitle("Help") }
            item {
                SettingsItem("Contact Us", R.drawable.ic_settings_phone)
            }
            item {
                SettingsItem("FAQ", R.drawable.ic_settings_document, onClick = onFaqClick)
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF7754F6)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 1.dp,
                        brush = SolidColor(Color(0xFF7754F6))
                    )
                ) {
                    Text("Log Out", fontFamily = poppinsBold)
                }
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        fontFamily = poppinsBold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SettingsItem(label: String, iconRes: Int, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFF4F4F4)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            label,
            fontFamily = poppinsRegular,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Arrow",
            tint = Color.Black
        )
    }
}
