package com.parcial.tp3.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.parcial.tp3.navigation.Screen

/**
 * BottomNavBar que muestra 4 íconos fijos:
 *  - Home
 *  - Reloj (DummyClock)
 *  - Carrito (Cart)
 *  - Perfil (Profile)
 *
 * Cuando tocan cada ícono, navegamos a la ruta indicada.
 */
@Composable
fun BottomNavBar(navController: NavHostController) {
    // Solo estas cuatro pantallas tienen iconRes y label no-null:
    val items = listOf(
        Screen.Home,
        Screen.DummyClock,
        Screen.Cart,
        Screen.Profile
    )

    NavigationBar {
        // Ruta actual (para resaltar el ítem seleccionado)
        val currentDestination: NavDestination? = navController.currentBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    // Forzamos non-null porque sabemos que estas cuatro pantallas sí tienen iconRes
                    Icon(
                        painter = painterResource(id = screen.iconRes!!),
                        contentDescription = screen.label
                    )
                },
                label = { Text(text = screen.label!!) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    val currentRoute = currentDestination?.route
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
