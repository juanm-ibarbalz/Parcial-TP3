package com.parcial.tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.parcial.tp3.navigation.AppNavGraph
import com.parcial.tp3.ui.theme.ParcialTP3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Forzamos fondo blanco completo para evitar el gris (#FAFAFA) por defecto del sistema
        window.setBackgroundDrawableResource(android.R.color.white)

        setContent {
            ParcialTP3Theme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}

