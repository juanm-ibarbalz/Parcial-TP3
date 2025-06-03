// app/src/main/java/com/parcial/tp3/navigation/Screen.kt

package com.parcial.tp3.navigation

import androidx.annotation.DrawableRes
import com.parcial.tp3.R

/**
 * Cada objeto Screen describe una ruta de navegación.
 * Solo los que van en el BottomNavBar llevan iconRes y label.
 */
sealed class Screen(
    val route: String,
    @DrawableRes val iconRes: Int? = null,
    val label: String? = null
) {
    // — Pantallas del BottomNavBar (solo estas llevan iconRes + label) —
    object Home : Screen(
        route = "home",
        iconRes = R.drawable.ic_navbar_home,
        label = "Home"
    )
    object Cart : Screen(
        route = "cart",
        iconRes = R.drawable.ic_navbar_bag,
        label = "Carrito"
    )
    object DummyClock : Screen(
        route = "clock",
        iconRes = R.drawable.ic_navbar_clock,
        label = "Reloj"
    )
    object Profile : Screen(
        route = "profile",
        iconRes = R.drawable.ic_navbar_profile,
        label = "Perfil"
    )

    object Register : Screen(
        route = "register",
        iconRes = null,
        label = "Registro"
    )

    // — Rutas que no aparecen en BottomNavBar (no llevan iconRes ni label) —
    object Login : Screen(route = "login")
    object ProductDetail : Screen(route = "productDetail/{productId}") {
        fun createRoute(productId: Int) = "productDetail/$productId"
    }
    object Settings : Screen(route = "settings")
    object Notifications : Screen(route = "notifications")
    object BestSellers : Screen(route = "best_sellers")
    object Search : Screen(route = "search")
}
