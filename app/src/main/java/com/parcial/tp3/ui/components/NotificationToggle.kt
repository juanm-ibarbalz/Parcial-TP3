package com.parcial.tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NotificationToggle(selectedTab: String, onTabSelected: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .width(260.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF0F0F0)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleTab("Activity", selectedTab, onTabSelected, modifier = Modifier.weight(1f))
            ToggleTab("Seller Mode", selectedTab, onTabSelected, modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun ToggleTab(
    label: String,
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = selected == label

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = if (isSelected) Color(0xFF9C27FF) else Color.Transparent
            )
            .clickable { onSelect(label) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Gray
        )
    }
}
