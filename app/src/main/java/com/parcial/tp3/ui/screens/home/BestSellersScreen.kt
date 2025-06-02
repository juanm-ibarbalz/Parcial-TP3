package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.parcial.tp3.navigation.Screen
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.components.ProductCard
import com.parcial.tp3.ui.screens.cart.CartViewModel
import com.parcial.tp3.ui.theme.BackgroundWhite
import kotlinx.coroutines.launch

@Composable
fun BestSellersScreen(
    navController: NavHostController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val products = viewModel.allProducts
    val cartViewModel: CartViewModel = hiltViewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadAllProducts(limit = 20)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundWhite)
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
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
                        onAddClick = {
                            cartViewModel.addToCart(product.id)
                            scope.launch {
                                snackbarHostState.showSnackbar("Agregado al carrito: ${product.name}")
                            }
                        },
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
}
