package com.parcial.tp3.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.parcial.tp3.domain.model.Product
import com.parcial.tp3.shared.IProductService

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productService: IProductService
) : ViewModel() {

    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadProducts() {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            try {
                products = productService.getAll()
            } catch (e: Exception) {
                errorMessage = "Error al cargar productos"
            } finally {
                isLoading = false
            }
        }
    }
}
