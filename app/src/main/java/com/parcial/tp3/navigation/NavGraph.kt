package com.parcial.tp3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.parcial.tp3.ui.screens.home.BestSellersScreen
import com.parcial.tp3.ui.screens.home.HomeScreen
import com.parcial.tp3.ui.screens.home.NotificationScreen
import com.parcial.tp3.ui.screens.home.SearchScreen
import com.parcial.tp3.ui.screens.product_detail.ProductDetailScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Login.route) {
            // TODO: LoginScreen(navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            ProductDetailScreen(navController = navController, productId = productId)
        }
        composable(route = Screen.Cart.route) {
            // TODO: CartScreen()
        }
        composable(route = Screen.Settings.route) {
            // TODO: SettingsScreen()
        }
        composable(route = Screen.Notifications.route) {
            NotificationScreen(navController = navController)
        }
        composable(route = Screen.BestSellers.route) {
            BestSellersScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}