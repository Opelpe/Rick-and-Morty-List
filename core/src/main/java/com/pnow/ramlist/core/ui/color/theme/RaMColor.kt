package com.pnow.ramlist.core.ui.color.theme

import androidx.compose.ui.graphics.Color
import com.pnow.ramlist.core.ui.color.palette.RaMPalette

object RaMColor {

    // — Brand / Primary —
    val Primary = RaMPalette.Fire
    val PrimaryDark = RaMPalette.FireDark
    val PrimaryTransparent = RaMPalette.Fire.copy(alpha = 0.64f)

    // — Surface —
    val SurfaceDefault = RaMPalette.FireLight
    val SurfaceVariant = RaMPalette.DeepBrown
    val SurfaceVariantTransparent = RaMPalette.DeepBrown.copy(alpha = 0.45f)

    // — Background —
    val BackgroundLight = RaMPalette.White
    val BackgroundDark = RaMPalette.Black

    // — Text —
    val TextOnDark = RaMPalette.White
    val TextOnLight = RaMPalette.Black

    // — Neutrals —
    val GreenDark = RaMPalette.GreenDark
    val GreenTransparent = RaMPalette.GreenTransparentRaw
    val Grey = RaMPalette.Grey
    val GreyTransparent = RaMPalette.GreyLight.copy(alpha = 0.88f)
    val White = RaMPalette.White
    val Black = RaMPalette.Black

    fun selectableItemBackground(darkTheme: Boolean, isSelected: Boolean, isPressed: Boolean): Color = when {
        isSelected || isPressed -> if (darkTheme) GreenTransparent else PrimaryTransparent
        else -> if (darkTheme) PrimaryTransparent else GreyTransparent
    }

    fun selectableItemBorder(darkTheme: Boolean, isSelected: Boolean, isPressed: Boolean): Color = when {
        isSelected || isPressed -> if (darkTheme) PrimaryDark else Primary
        else -> if (darkTheme) RaMPalette.GreenDark else PrimaryDark
    }

}
