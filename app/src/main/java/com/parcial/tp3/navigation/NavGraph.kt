package com.parcial.tp3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            // TODO: LoginScreen(navController)
        }
        composable(route = Screen.Home.route) {
            // TODO: HomeScreen()
        }
        composable(route = Screen.ProductDetail.route) {
            // TODO: ProductDetailScreen()
        }
        composable(route = Screen.Cart.route) {
            // TODO: CartScreen()
        }
        composable(route = Screen.Settings.route) {
            // TODO: SettingsScreen()
        }
    }
}
