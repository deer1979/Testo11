
package com.deercom.testo11
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Dark = darkColorScheme(
    primary = Color(0xFF0B3D91),
    secondary = Color(0xFF2E3A59),
    background = Color(0xFF0F172A),
    surface = Color(0xFF111827),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFE5E7EB),
    onSurface = Color(0xFFE5E7EB),
)

@Composable
fun AppTheme(content:@Composable ()->Unit) { MaterialTheme(colorScheme = Dark, content = content) }
