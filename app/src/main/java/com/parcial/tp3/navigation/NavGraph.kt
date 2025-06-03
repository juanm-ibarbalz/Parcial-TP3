// app/src/main/java/com/parcial/tp3/navigation/NavGraph.kt

package com.parcial.tp3.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.parcial.tp3.ui.screens.cart.CartScreen
import com.parcial.tp3.ui.screens.home.BestSellersScreen
import com.parcial.tp3.ui.screens.home.HomeScreen
import com.parcial.tp3.ui.screens.home.NotificationScreen
import com.parcial.tp3.ui.screens.home.SearchScreen
import com.parcial.tp3.ui.screens.login.LoginScreen
import com.parcial.tp3.ui.screens.profile.ProfileScreen
import com.parcial.tp3.ui.screens.register.RegisterScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        // 1. LoginScreen real
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        // 2. RegisterScreen
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        // 3. HomeScreen real
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        // 4. ProductDetail con argumento (aún pendiente de llamar al composable real)
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            // Aquí deberías invocar tu ProductDetailScreen(productId, navController)
        }

        // 5. Clock dummy
        composable(route = Screen.DummyClock.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Pantalla Reloj (dummy)")
            }
        }

        // 6. CartScreen real
        composable(route = Screen.Cart.route) {
            CartScreen(navController = navController)
        }

        // 7. Settings placeholder (opcional)
        composable(route = Screen.Settings.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Pantalla Ajustes (pendiente)")
            }
        }

        // 8. NotificationScreen real
        composable(route = Screen.Notifications.route) {
            NotificationScreen(navController = navController)
        }

        // 9. BestSellersScreen real
        composable(route = Screen.BestSellers.route) {
            BestSellersScreen(navController = navController)
        }

        // 10. SearchScreen real
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }

        // 11. ProfileScreen real
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}
