package com.parcial.tp3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.parcial.tp3.R
import com.parcial.tp3.domain.model.CartItem
import com.parcial.tp3.ui.theme.BackgroundWhite
import com.parcial.tp3.ui.theme.MediumGrey
import com.parcial.tp3.ui.theme.PrimaryBlue
import com.parcial.tp3.ui.theme.PureBlack

import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartItemCard(
    item: CartItem,
    modifier: Modifier = Modifier,
    onRemove: () -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                onRemove()
            }
            false
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color(0xFFF2F2F2)),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = "Delete",
                    tint = Color.Red,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .size(28.dp)
                )
            }
        },
        dismissContent = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(115.dp)
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp), clip = false)
                    .clip(RoundedCornerShape(24.dp))
                    .background(BackgroundWhite)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_product_goldenbag_cat),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = PureBlack
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "for 2–3 years",
                        style = MaterialTheme.typography.bodySmall,
                        color = MediumGrey
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$${String.format(java.util.Locale.US, "%.2f", item.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryBlue
                    )
                }
            }
        }
    )
}
