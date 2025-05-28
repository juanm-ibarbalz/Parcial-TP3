package com.parcial.tp3.ui.screens.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parcial.tp3.domain.model.Product
import com.parcial.tp3.shared.IProductService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productService: IProductService
) : ViewModel() {

    var product by mutableStateOf<Product?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadProductById(productId: Int) {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            try {
                product = productService.getById(productId)
            } catch (e: Exception) {
                errorMessage = "Error al cargar el producto"
            } finally {
                isLoading = false
            }
        }
    }
}
