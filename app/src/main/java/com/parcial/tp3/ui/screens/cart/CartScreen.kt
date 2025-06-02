package com.parcial.tp3.ui.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.CartItemCard
import com.parcial.tp3.ui.components.CheckoutSummary
import com.parcial.tp3.ui.components.NotificationHeader
import android.util.Log

@Composable
fun CartScreen(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadCart()
    }

    val items = viewModel.cartItems
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            NotificationHeader(navController = navController, title = "Cart")
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (error != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = error)
                }
            } else if (items.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Tu carrito está vacío")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val duplicatedItems = items.flatMap { item ->
                        List(item.quantity) { index ->
                            item.copy(visualId  = "${item.productId}_$index".hashCode())
                        }
                    }

                    items(duplicatedItems, key = { it.visualId }) { item ->
                        CartItemCard(
                            item = item,
                            onRemove = { viewModel.removeItem(item.productId) }
                        )
                    }
                }
            }
        }

        CheckoutSummary(
            itemCount = items.size,
            subtotal = items.sumOf { it.price },
            tax = items.sumOf { it.price } * 0.21,
            total = items.sumOf { it.price } + items.sumOf { it.price } * 0.21,
            onCheckout = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}
