package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.parcial.tp3.navigation.Screen
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.components.ProductCard
import com.parcial.tp3.ui.theme.BackgroundWhite

@Composable
fun BestSellersScreen(
    navController: NavHostController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val products = viewModel.bestSellers

    LaunchedEffect(Unit) {
        viewModel.loadBestSellers(limit = 20)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .padding(horizontal = 16.dp)
    ) {
        NotificationHeader(navController = navController, title = "Best Seller")

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
        }
    }
}
