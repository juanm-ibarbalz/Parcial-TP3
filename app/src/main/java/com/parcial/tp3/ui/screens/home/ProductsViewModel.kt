package com.parcial.tp3.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.parcial.tp3.domain.model.ProductPreview
import com.parcial.tp3.shared.IProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productService: IProductService
) : ViewModel() {

    var bestSellers by mutableStateOf<List<ProductPreview>>(emptyList())
        private set

    var allProducts by mutableStateOf<List<ProductPreview>>(emptyList())
        private set

    var searchQuery by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun loadBestSellers(limit: Int = 2, skip: Int = 0, category: String? = null) {
        if (allProducts.isNotEmpty()) return

        isLoading = true
        errorMessage = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = if (category != null) {
                    productService.getByCategory(category, limit, skip)
                } else {
                    productService.getAll(limit, skip)
                }

                val previews = products.map {
                    ProductPreview(
                        id = it.id,
                        name = it.title,
                        price = it.price,
                        image = it.thumbnail
                    )
                }

                withContext(Dispatchers.Main) {
                    bestSellers = previews
                    allProducts = previews
                    isLoading = false
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage = "Error al cargar los best sellers"
                    isLoading = false
                }
            }
        }
    }
}
