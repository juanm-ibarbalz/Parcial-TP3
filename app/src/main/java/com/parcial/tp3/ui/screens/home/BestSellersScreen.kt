package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.ProductCard
import androidx.hilt.navigation.compose.hiltViewModel
import com.parcial.tp3.ui.components.NotificationHeader

@Composable
fun BestSellersScreen(
    navController: NavHostController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val products = viewModel.bestSellers
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.loadBestSellers(limit = 20)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NotificationHeader(navController = navController, title = "Best Seller")

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Text(text = error)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    ProductCard(
                        name = product.name,
                        price = String.format("%.2f", product.price),
                        imageUrl = product.image,
                        onAddClick = { /* TODO */ },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }
    }
}
