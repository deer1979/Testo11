package com.deercom.testo11

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

// Paleta sobria (azul/gris)
private val Primary = Color(0xFF1E3A8A)  // azul profundo
private val OnPrimary = Color(0xFFFFFFFF)
private val Secondary = Color(0xFF334155) // gris azulado
private val OnSecondary = Color(0xFFE2E8F0)
private val BackgroundLight = Color(0xFFF8FAFC)
private val SurfaceLight = Color(0xFFFFFFFF)
private val TextPrimaryLight = Color(0xFF0F172A) // slate-900
private val OutlineLight = Color(0xFFCBD5E1)

private val BackgroundDark = Color(0xFF0B1220)
private val SurfaceDark = Color(0xFF0F172A)
private val TextPrimaryDark = Color(0xFFE5E7EB) // slate-200
private val OutlineDark = Color(0xFF334155)

private val LightColors: ColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = BackgroundLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    outline = OutlineLight
)

private val DarkColors: ColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = BackgroundDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    outline = OutlineDark
)

// Tipografía clara y profesional
private val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
