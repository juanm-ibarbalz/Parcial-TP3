// app/src/main/java/com/parcial/tp3/ui/screens/profile/ProfileViewModel.kt

package com.parcial.tp3.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.parcial.tp3.data.session.UserData
import com.parcial.tp3.domain.model.Product         // ← Importa el Product de dominio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    // Aquí podrías inyectar tu repositorio real si lo necesitas.
) : ViewModel() {

    // Flow con la lista de productos del vendedor (solo ejemplos visuales)
    private val _myProducts = MutableStateFlow<List<Product>>(emptyList())
    val myProducts: StateFlow<List<Product>> = _myProducts

    /** Simulación: carga productos para el vendedor actual. */
    fun loadMyProducts(userId: String) {
        // Para propósitos meramente ilustrativos, usando Product de dominio.
        _myProducts.value = listOf(
            // Los IDs son Int en domain.model.Product, así que convertimos a Int
            Product(
                id = 1,
                title = "RC Kitten",
                price = 20.99,
                thumbnail = "https://dummyimage.com/200x200/ffb6c1/ffffff&text=Kitten",
                category = "Cats",
                description = "A cute remote-controlled kitten"
            ),
            Product(
                id = 2,
                title = "RC Persian",
                price = 22.99,
                thumbnail = "https://dummyimage.com/200x200/ffe4e1/ffffff&text=Persian",
                category = "Cats",
                description = "A fluffy remote-controlled Persian cat"
            ),
            Product(
                id = 3,
                title = "RC Siamese",
                price = 25.49,
                thumbnail = "https://dummyimage.com/200x200/dda0dd/ffffff&text=Siamese",
                category = "Cats",
                description = "An elegant remote-controlled Siamese cat"
            )
        )
    }

    /** Simulación: abre pantalla de edición del perfil. */
    fun openEditProfileScreen(currentUser: UserData) {
        // Sin lógica: solo ilustrativo.
    }

    /** Simulación: abre pantalla para agregar producto. */
    fun openAddProductScreen() {
        // Sin lógica: solo ilustrativo.
    }

    /** Simulación: actualizar precio de un producto. */
    fun updateProductPrice(productId: Int, newPrice: Double) {
        _myProducts.update { list ->
            list.map { product ->
                if (product.id == productId) product.copy(price = newPrice)
                else product
            }
        }
    }

    /** Simulación: eliminar un producto. */
    fun deleteProduct(productId: Int) {
        _myProducts.update { list ->
            list.filterNot { it.id == productId }
        }
    }

    /** Simulación: actualizar perfil del usuario. */
    fun updateUserProfile(userId: String, newName: String, newAvatarUrl: String) {
        // Solo ilustrativo: no hace nada real.
    }

    /** Simulación: crear un nuevo producto. */
    fun createProduct(sellerId: String, name: String, imageUrl: String, price: Double) {
        // Solo ilustrativo: no hace nada real.
    }
}
