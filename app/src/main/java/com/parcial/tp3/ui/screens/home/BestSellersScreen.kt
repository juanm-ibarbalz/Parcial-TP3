package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.components.ProductCard
import com.parcial.tp3.navigation.Screen

@Composable
fun BestSellersScreen(
    navController: NavHostController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val products = viewModel.bestSellers

    LaunchedEffect(Unit) {
        viewModel.loadBestSellers(limit = 20)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        NotificationHeader(navController = navController, title = "Best Sellers")

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products) { product ->
                ProductCard(
                    productId = product.id,
                    name = product.name,
                    price = String.format(java.util.Locale.US, "%.2f", product.price),
                    imageUrl = product.image,
                    onAddClick = { /* TODO */ },
                    onCardClick = {
                        navController.navigate(Screen.ProductDetail.createRoute(product.id))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
