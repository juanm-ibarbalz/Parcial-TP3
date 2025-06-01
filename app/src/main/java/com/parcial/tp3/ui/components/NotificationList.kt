package com.parcial.tp3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.parcial.tp3.R
import com.parcial.tp3.ui.theme.MediumGrey
import com.parcial.tp3.ui.theme.PureBlack

data class NotificationItem(
    val title: String,
    val subtitle: String,
    val imageRes: Int,
    val type: NotificationType
)

enum class NotificationType {
    ORDER, LIKE
}

private val activityItems = List(5) {
    NotificationItem(
        title = "SALE 50%",
        subtitle = "Check the details!",
        imageRes = R.drawable.image_product_goldenbag_cat,
        type = NotificationType.ORDER
    )
}

private val sellerItems = listOf(
    NotificationItem("You Got New Order!", "Please arrange delivery", R.drawable.image_product_goldenbag_cat, NotificationType.ORDER),
    NotificationItem("Momon", "Liked your Product", R.drawable.ilustration_pfp_2, NotificationType.LIKE),
    NotificationItem("Ola", "Liked your Product", R.drawable.ilustration_pfp_2, NotificationType.LIKE),
    NotificationItem("Raul", "Liked your Product", R.drawable.ilustration_pfp_2, NotificationType.LIKE),
    NotificationItem("You Got New Order!", "Please arrange delivery", R.drawable.image_product_goldenbag_cat, NotificationType.ORDER),
    NotificationItem("Vito", "Liked your Product", R.drawable.ilustration_pfp_2, NotificationType.LIKE)
)

@Composable
fun NotificationList(selectedTab: String) {
    val items = remember(selectedTab) {
        if (selectedTab == "Activity") activityItems else sellerItems
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(items) { item ->
            NotificationCard(item = item)
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(52.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelMedium,
                color = PureBlack
            )
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MediumGrey
            )
        }

        if (item.type == NotificationType.ORDER) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Go",
                modifier = Modifier.size(24.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_product_pinkbag),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}
