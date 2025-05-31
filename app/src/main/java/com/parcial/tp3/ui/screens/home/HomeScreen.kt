package com.parcial.tp3.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.parcial.tp3.R
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.parcial.tp3.ui.components.BestSellerSection
import com.parcial.tp3.ui.components.PromoBannerCard
import com.parcial.tp3.ui.components.TopIcons
import com.parcial.tp3.ui.components.CategorySection
import com.parcial.tp3.ui.components.LocationDisplay


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ProductsViewModel = hiltViewModel()
) {

    val categories = listOf("Beauty", "Tops", "Smartphones")
    var selectedCategory = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selectedCategory.value) {
        Log.d("Filtro", "Categoría seleccionada: $selectedCategory")
        viewModel.loadBestSellers(limit = 2, category = selectedCategory.value?.lowercase())
    }

    val products = viewModel.bestSellers
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            LocationDisplay()
            TopIcons(
                navController = navController,
                onSearchClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            PromoBannerCard(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )

            Image(
                painter = painterResource(id = R.drawable.image_product_goldenbag_dog),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = (-25).dp, y = (-30).dp)
                    .zIndex(0f),
                contentScale = ContentScale.Fit
            )

            Image(
                painter = painterResource(id = R.drawable.image_product_goldenbag_dog),
                contentDescription = null,
                modifier = Modifier
                    .size(185.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = 15.dp, y = 20.dp)
                    .zIndex(1f),
                contentScale = ContentScale.Fit
            )
        }

        CategorySection(
            categories = categories,
            selectedCategory = selectedCategory.value,
            onCategorySelected = { category ->
                if (selectedCategory.value != category) {
                    selectedCategory.value = category
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text("Error al cargar productos")
        } else {
            BestSellerSection(products = products, onAddClick = { /* TODO */ })
        }
    }
}



