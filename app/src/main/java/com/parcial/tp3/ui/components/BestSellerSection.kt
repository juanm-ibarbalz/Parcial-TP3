package com.parcial.tp3.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.parcial.tp3.domain.model.ProductPreview
import com.parcial.tp3.ui.theme.PrimaryBlue


@Composable
fun BestSellerSection(
    products: List<ProductPreview>,
    onAddClick: (ProductPreview) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Best Seller",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "View All",
                color = PrimaryBlue,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.clickable { /* TODO: Handle click */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            products.forEach { product ->
                ProductCard(
                    name = product.name,
                    price = String.format("%.2f", product.price),
                    imageUrl = product.image,
                    onAddClick = { onAddClick(product) }
                )
            }
        }

    }
}
