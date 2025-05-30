package com.parcial.tp3.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.parcial.tp3.domain.model.ProductPreview
import com.parcial.tp3.shared.IProductService

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productService: IProductService
) : ViewModel() {

    var bestSellers by mutableStateOf<List<ProductPreview>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadBestSellers(limit: Int = 2, skip: Int = 0, category: String? = null) {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
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
                bestSellers = previews
            } catch (e: Exception) {
                errorMessage = "Error al cargar los best sellers"
            } finally {
                isLoading = false
            }
        }
    }
}
