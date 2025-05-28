package com.parcial.tp3.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object ProductDetail : Screen("productDetail/{productId}") {
        fun createRoute(productId: Int) = "productDetail/$productId"
    }
    object Cart : Screen("cart")
    object Settings : Screen("settings")
}