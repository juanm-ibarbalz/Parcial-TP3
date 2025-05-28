package com.parcial.tp3.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.parcial.tp3.domain.model.CartItem
import com.parcial.tp3.shared.ICartService

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartService: ICartService
) : ViewModel() {

    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadCart(userId: Int) {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            try {
                cartItems = cartService.getCartByUserId(userId).products
            } catch (e: Exception) {
                errorMessage = "Error al cargar el carrito"
            } finally {
                isLoading = false
            }
        }
    }
}
