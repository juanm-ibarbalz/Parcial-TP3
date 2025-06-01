package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.parcial.tp3.R
import com.parcial.tp3.ui.components.CategorySection
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.components.ProductCard
import com.parcial.tp3.ui.theme.MediumGrey

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val searchText = viewModel.searchQuery
    val allProducts = viewModel.allProducts
    val categories = listOf("Food", "Toys", "Accessories")
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val filteredProducts by remember(searchText, allProducts) {
        derivedStateOf {
            if (searchText.isBlank()) emptyList()
            else allProducts.filter { it.name.contains(searchText, ignoreCase = true) }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadBestSellers(limit = 20)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        NotificationHeader(navController = navController, title = "Search")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { viewModel.updateSearchQuery(it) },
            placeholder = { Text("Search your Product", style = MaterialTheme.typography.labelMedium,
                color = MediumGrey) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F5F5),
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        CategorySection(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            showViewAll = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Recent",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(filteredProducts) { product ->
                ProductCard(
                    productId = product.id,
                    name = product.name,
                    price = String.format(java.util.Locale.US, "%.2f", product.price),
                    imageUrl = product.image,
                    onAddClick = { /* TODO */ },
                    onCardClick = {
                        navController.navigate(com.parcial.tp3.navigation.Screen.ProductDetail.createRoute(product.id))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
