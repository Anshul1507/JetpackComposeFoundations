package com.anshul1507.composesamplefirst.practice.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)

// 1. Dark Theme Palette
val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF1C1B1F),
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

// 2. Light Theme Palette
val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
)