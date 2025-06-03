package com.parcial.tp3.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.parcial.tp3.ui.components.ButtonBottom
import com.parcial.tp3.ui.components.NotificationHeader
import com.parcial.tp3.ui.theme.LightSurfaceGrey
import com.parcial.tp3.ui.theme.MediumGrey
import com.parcial.tp3.ui.theme.PureBlack
import androidx.compose.runtime.mutableIntStateOf
import com.parcial.tp3.ui.screens.cart.CartViewModel
import com.parcial.tp3.ui.screens.favourites.FavouriteViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: Int,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val product = viewModel.product
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage
    val cartViewModel: CartViewModel = hiltViewModel()
    val favouriteViewModel: FavouriteViewModel = hiltViewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var quantity by remember { mutableIntStateOf(1) }
    val isFavourite = remember { mutableStateOf(false) }

    LaunchedEffect(product) {
        product?.let {
            val fav = favouriteViewModel.isFavourite(it.id)
            isFavourite.value = fav
        }
    }

    LaunchedEffect(productId) {
        viewModel.loadProductById(productId)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            NotificationHeader(
                navController = navController,
                title = "Product Detail",
                showFavoriteIcon = true,
                isFavourite = isFavourite.value,
                onToggleFavourite = {
                    product?.let {
                        isFavourite.value = if (isFavourite.value) {
                            favouriteViewModel.removeFromFavourites(it)
                            false
                        } else {
                            favouriteViewModel.addToFavourites(it)
                            true
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                error != null -> {
                    Text(text = error)
                }

                product != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(LightSurfaceGrey),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(product.thumbnail),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = PureBlack
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MediumGrey
                    )

                    Spacer(modifier = Modifier.weight(10f))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFFF2F2F2))
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RoundedCounterButton("-") {
                                    if (quantity > 1) quantity--
                                }
                                Text(
                                    text = quantity.toString(),
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                RoundedCounterButton("+") {
                                    quantity++
                                }
                            }
                        }


                        Text(
                            text = "$${String.format("%.2f", product.price)}",
                            style = MaterialTheme.typography.displayMedium
                        )
                    }


                    Spacer(modifier = Modifier.weight(1f))

                    ButtonBottom(
                        text = "Add to Cart",
                        onClick = {
                            product.let {
                                cartViewModel.addToCart(productId = it.id, quantity = quantity)
                                scope.launch {
                                    snackbarHostState.showSnackbar("Agregado al carrito: ${it.title}")
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RoundedCounterButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color(0xFFF2F2F2))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}
