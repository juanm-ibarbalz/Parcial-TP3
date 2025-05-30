package com.parcial.tp3.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,         // AzulPrincipal – Botones, íconos activos
    secondary = LightGreyText,     // GrisSubtitulo – Subtítulos, texto secundario
    tertiary = AlertRed            // Rojo – Íconos de borrar, alertas
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,          // AzulPrincipal – Botones, íconos activos
    secondary = LightGreyText,      // GrisSubtitulo – Subtítulos, texto secundario
    tertiary = AlertRed,            // Rojo – Íconos de borrar, alertas
    background = BackgroundWhite,
    surface = BackgroundWhite,
    onBackground = PureBlack
)

@Composable
fun ParcialTP3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
