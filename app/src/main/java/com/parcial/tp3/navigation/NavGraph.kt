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
import com.parcial.tp3.ui.screens.onboarding.OnboardingScreen
import com.parcial.tp3.ui.screens.product_detail.ProductDetailScreen
import com.parcial.tp3.ui.screens.profile.ProfileScreen
import com.parcial.tp3.ui.screens.register.RegisterScreen
import com.parcial.tp3.ui.screens.settings.AccountScreen
import com.parcial.tp3.ui.screens.settings.ChangeEmailScreen
import com.parcial.tp3.ui.screens.settings.ChangePasswordScreen
import com.parcial.tp3.ui.screens.settings.FaqScreen
import com.parcial.tp3.ui.screens.settings.PrivacyScreen
import com.parcial.tp3.ui.screens.settings.SecurityScreen
import com.parcial.tp3.ui.screens.settings.SettingsDetailScreen
import com.parcial.tp3.ui.screens.settings.NotificationSettingsScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
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
            CartScreen(navController = navController)
        }

        composable(route = Screen.DummyClock.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Pantalla Reloj (dummy)")
            }
        }

        composable(route = Screen.Settings.route) {
            SettingsDetailScreen(
                navController = navController,
                onLogoutClick = { /* lógica de logout */ },
                onAccountClick = { navController.navigate("account") },
                onNotificationClick = { navController.navigate("NotificationSettingsScreen") },
                onPrivacyClick = { navController.navigate("privacy") },
                onSecurityClick = { navController.navigate("security") },
                onFaqClick = { navController.navigate("faq") }
            )
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

        composable(route = Screen.Cart.route) {
            CartScreen(navController = navController)
        }

        composable("account") {
            AccountScreen(
                navController = navController
            )
        }

        composable("NotificationSettingsScreen") {
            NotificationSettingsScreen(navController = navController)
        }
        composable("privacy") {
            PrivacyScreen(navController = navController)
        }

        composable("security") {
            SecurityScreen(
                navController = navController,
                onChangePasswordClick = { navController.navigate("changePassword") },
                onChangeEmailClick = { navController.navigate("changeEmail") }
            )
        }

        composable("changePassword") {
            ChangePasswordScreen(navController = navController)
        }

        composable("changeEmail") {
            ChangeEmailScreen(navController = navController)
        }

        composable("faq") {
            FaqScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }

        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
    }
}
