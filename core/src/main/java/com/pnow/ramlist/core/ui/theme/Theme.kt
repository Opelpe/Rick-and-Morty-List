package com.pnow.ramlist.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.pnow.ramlist.core.ui.color.details.CharacterDetailsColors
import com.pnow.ramlist.core.ui.color.details.DarkCharacterDetailsColors
import com.pnow.ramlist.core.ui.color.details.LightCharacterDetailsColors
import com.pnow.ramlist.core.ui.color.theme.RaMColor

private val LightColorScheme = lightColorScheme(
    primary = RaMColor.Primary,
    secondary = RaMColor.PrimaryDark,
    background = RaMColor.BackgroundLight,
    surface = RaMColor.SurfaceDefault,
    onPrimary = RaMColor.White,
    onBackground = RaMColor.Black,
    onSurface = RaMColor.Black,
    surfaceVariant = RaMColor.SurfaceVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = RaMColor.PrimaryDark,
    secondary = RaMColor.GreenDark,
    background = RaMColor.BackgroundDark,
    surface = RaMColor.Grey,
    onPrimary = RaMColor.Grey,
    onBackground = RaMColor.White,
    onSurface = RaMColor.White,
    surfaceVariant = RaMColor.SurfaceVariantTransparent
)

val LocalCharacterDetailsColors = staticCompositionLocalOf {
    LightCharacterDetailsColors
}

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val characterColors = if (darkTheme) DarkCharacterDetailsColors else LightCharacterDetailsColors
    CompositionLocalProvider(
        LocalCharacterDetailsColors provides characterColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

val MaterialTheme.detailsColors: CharacterDetailsColors
    @Composable get() = LocalCharacterDetailsColors.current
