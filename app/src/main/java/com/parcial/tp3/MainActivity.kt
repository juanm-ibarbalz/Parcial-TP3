// app/src/main/java/com/parcial/tp3/MainActivity.kt

package com.parcial.tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.parcial.tp3.navigation.AppNavGraph
import com.parcial.tp3.navigation.Screen
import com.parcial.tp3.ui.components.BottomNavBar
import com.parcial.tp3.ui.theme.ParcialTP3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setBackgroundDrawableResource(android.R.color.white)

        setContent {
            ParcialTP3Theme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    // Observamos la ruta actual para decidir si mostramos la BottomNavBar
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            bottomBar = {
                if (currentRoute == Screen.Home.route) {
                    BottomNavBar(navController = navController)
                }
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
