    package com.parcial.tp3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.parcial.tp3.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

// Typography styles used across the app
val AppTypography = Typography(
    displayLarge = TextStyle( // Poppins SemiBold 40sp
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp
    ),
    displayMedium = TextStyle( // Poppins SemiBold 24sp
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle( // Poppins SemiBold 20sp
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle( // Poppins Regular 12sp
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle( // Poppins Regular 14sp
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle( // Poppins Regular 16sp
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle( // Poppins Medium 12sp
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelMedium = TextStyle( // Poppins Medium 14sp
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle( // Poppins SemiBold 12sp
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    titleMedium = TextStyle( // Poppins SemiBold 18sp
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle( // Poppins Bold 16sp
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
)
