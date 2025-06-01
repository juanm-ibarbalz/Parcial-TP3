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

    var homePreviewProducts by mutableStateOf<List<ProductPreview>>(emptyList())
        private set

    var allProducts by mutableStateOf<List<ProductPreview>>(emptyList())
        private set

    var searchResults by mutableStateOf<List<ProductPreview>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadHomePreviewProducts(limit: Int = 2, skip: Int = 0, category: String? = null) {
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
                        image = it.thumbnail,
                        category = it.category
                    )
                }

                withContext(Dispatchers.Main) {
                    homePreviewProducts = previews
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

    fun loadAllProducts(limit: Int = 20, skip: Int = 0) {
        if (allProducts.isNotEmpty()) return

        isLoading = true
        errorMessage = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = productService.getAll(limit, skip)

                val previews = products.map {
                    ProductPreview(
                        id = it.id,
                        name = it.title,
                        price = it.price,
                        image = it.thumbnail,
                        category = it.category
                    )
                }

                withContext(Dispatchers.Main) {
                    allProducts = previews
                    isLoading = false
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage = "Error al cargar los productos"
                    isLoading = false
                }
            }
        }
    }

    fun searchProducts(query: String) {
        isLoading = true
        errorMessage = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = productService.search(query)
                val previews = products.map {
                    ProductPreview(
                        id = it.id,
                        name = it.title,
                        price = it.price,
                        image = it.thumbnail,
                        category = it.category
                    )
                }

                withContext(Dispatchers.Main) {
                    searchResults = previews
                    isLoading = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage = "Error al buscar productos"
                    isLoading = false
                }
            }
        }
    }

    fun clearSearchResults() {
        searchResults = emptyList()
    }
}

