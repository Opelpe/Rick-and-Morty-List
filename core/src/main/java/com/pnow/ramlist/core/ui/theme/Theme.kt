package com.pnow.ramlist.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = RaMColor.Fire,
    secondary = RaMColor.FireDark,
    background = RaMColor.White,
    surface = RaMColor.DetailsBackground,
    onPrimary = RaMColor.White,
    onBackground = RaMColor.Black,
    onSurface = RaMColor.Black,
    surfaceVariant = RaMColor.SurfaceVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = RaMColor.FireDark,
    secondary = RaMColor.GreenDark,
    background = RaMColor.Black,
    surface = RaMColor.Grey,
    onPrimary = RaMColor.Grey,
    onBackground = RaMColor.White,
    onSurface = RaMColor.White,
    surfaceVariant = RaMColor.SurfaceVariantTransparent
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
