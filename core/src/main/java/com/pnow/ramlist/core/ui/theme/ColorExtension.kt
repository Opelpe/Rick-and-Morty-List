package com.pnow.ramlist.core.ui.theme

import androidx.compose.ui.graphics.Color

fun itemSelectableBackground(
    darkTheme: Boolean,
    isSelected: Boolean,
    isPressed: Boolean
): Color {
    return when {
        isSelected || isPressed -> if (darkTheme) RaMColor.GreenTransparent else RaMColor.FireTransparent
        else -> if (darkTheme) RaMColor.FireTransparent else RaMColor.GreyTransparent
    }
}

fun itemSelectableBorder(
    darkTheme: Boolean,
    isSelected: Boolean,
    isPressed: Boolean
): Color {
    return when {
        isSelected || isPressed -> if (darkTheme) RaMColor.FireDark else RaMColor.Red
        else -> if (darkTheme) RaMColor.GreenDark else RaMColor.FireDark
    }
}
