package com.parcial.tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.parcial.tp3.ui.theme.BackgroundWhite
import com.parcial.tp3.ui.theme.PureBlack
import androidx.compose.ui.draw.shadow
import com.parcial.tp3.ui.theme.MediumGrey


@Composable
fun CheckoutSummary(
    itemCount: Int,
    subtotal: Double,
    tax: Double,
    total: Double,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                clip = false
            )
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(BackgroundWhite)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("$itemCount Items", style = MaterialTheme.typography.bodySmall, color = MediumGrey)
            Text("$${String.format("%.2f", subtotal)}", style = MaterialTheme.typography.bodySmall, color = MediumGrey)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Tax", style = MaterialTheme.typography.bodySmall, color = MediumGrey)
            Text("$${String.format("%.2f", tax)}", style = MaterialTheme.typography.bodySmall, color = MediumGrey)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Totals", style = MaterialTheme.typography.titleLarge)
            Text(
                "$${String.format("%.2f", total)}",
                style = MaterialTheme.typography.displayMedium,
                color = PureBlack
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ButtonBottom(text = "Checkout", onClick = onCheckout)
    }
}
