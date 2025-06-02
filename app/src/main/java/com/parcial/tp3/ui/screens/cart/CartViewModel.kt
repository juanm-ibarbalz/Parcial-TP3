    package com.parcial.tp3.ui.screens.cart

    import android.util.Log
    import androidx.lifecycle.ViewModel
    import dagger.hilt.android.lifecycle.HiltViewModel
    import javax.inject.Inject
    import androidx.compose.runtime.*
    import androidx.lifecycle.viewModelScope
    import com.parcial.tp3.data.remote.dto.CartProductRequestDto
    import com.parcial.tp3.domain.model.CartItem
    import com.parcial.tp3.shared.ICartService
    import kotlinx.coroutines.launch
    import com.parcial.tp3.utils.Constants

    @HiltViewModel
    class CartViewModel @Inject constructor(
        private val cartService: ICartService
    ) : ViewModel() {

        var cartId by mutableStateOf<Int?>(null)
            private set

        var cartItems by mutableStateOf<List<CartItem>>(emptyList())
            private set

        var isLoading by mutableStateOf(false)
            private set

        var errorMessage by mutableStateOf<String?>(null)
            private set

        fun loadCart() {
            isLoading = true
            errorMessage = null
            viewModelScope.launch {
                try {
                    val result = cartService.getCartByUserId(Constants.USER_ID)
                    cartItems = result.products
                    cartId = result.id

                } catch (e: Exception) {
                    errorMessage = "Error al cargar el carrito"
                } finally {
                    isLoading = false
                }
            }

        }

        fun addToCart(productId: Int, quantity: Int = 1) {
            viewModelScope.launch {
                try {
                    val product = CartProductRequestDto(id = productId, quantity = quantity)

                    val updatedCart = if (cartId != null) {
                        cartService.updateCart(cartId!!, listOf(product))
                    } else {
                        cartService.createCart(Constants.USER_ID, listOf(product)) // Constante hardcodeada para el ejemplo
                    }

                    cartItems = updatedCart.products
                    cartId = updatedCart.id
                } catch (e: Exception) {
                    errorMessage = "No se pudo agregar el producto"
                }
            }
        }

        fun removeItem(productId: Int) {
            val updatedItems = cartItems.mapNotNull {
                when {
                    it.productId == productId && it.quantity > 1 -> it.copy(quantity = it.quantity - 1)
                    it.productId == productId && it.quantity == 1 -> null
                    else -> it
                }
            }

            val request = updatedItems.map {
                CartProductRequestDto(id = it.productId, quantity = it.quantity)
            }

            viewModelScope.launch {
                try {
                    if (cartId != null) {
                        val updatedCart = cartService.updateCart(cartId!!, request)
                        cartItems = updatedCart.products
                        cartId = updatedCart.id
                    }
                } catch (e: Exception) {
                    errorMessage = "Error al eliminar producto del carrito"
                }
            }
        }
    }
