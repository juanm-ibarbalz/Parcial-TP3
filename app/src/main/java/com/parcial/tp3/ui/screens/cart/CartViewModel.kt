    package com.parcial.tp3.ui.screens.cart

    import androidx.lifecycle.ViewModel
    import dagger.hilt.android.lifecycle.HiltViewModel
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

//        fun loadCart(userId: Int) {
//            isLoading = true
//            errorMessage = null
////            viewModelScope.launch {
////                try {
////                    cartItems = cartService.getCartByUserId(userId).products
////                } catch (e: Exception) {
////                    errorMessage = "Error al cargar el carrito"
////                } finally {
////                    isLoading = false
////                }
////            }
//
//        }

        fun loadCart() {
            cartItems = listOf(
                CartItem(1, "Royal Canin Adult", 40.00, 1, 40.00, 0.0, 40.00),
                CartItem(2, "Royal Canin Senior", 70.00, 1, 70.00, 0.0, 70.00),
                CartItem(3, "Royal Canin Baby's", 90.00, 1, 90.00, 0.0, 90.00)
            )
        }

        fun removeItem(item: CartItem) {
            cartItems = cartItems.filterNot { it.id == item.id }
        }
    }
