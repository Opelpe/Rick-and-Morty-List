package com.pnow.ramlist.core.ui.color.details

import androidx.compose.ui.graphics.Color

data class CharacterDetailsColors(
    val background: Color,
    val heroBackground: Color,
    val cardBackground: Color,
    val textPrimary: Color,
    val textMuted: Color,
    val statusPillBackground: Color,
    val statusPillText: Color,
    val statusAlive: Color,
    val statusDead: Color,
    val episodeBadgeBackground: Color,
    val error: Color,
    val avatarBackground: Color
)

val LightCharacterDetailsColors = CharacterDetailsColors(
    background = Color(0xFFF9F0EE),
    heroBackground = Color(0xFFEDD8D3),
    cardBackground = Color(0xFFFFFFFF),
    textPrimary = Color(0xFF2C1A17),
    textMuted = Color(0xFF9B7B74),
    statusPillBackground = Color(0xFF2C1A17),
    statusPillText = Color(0xFFF9F0EE),
    statusAlive = Color(0xFF5FCF7A),
    statusDead = Color(0xFFE24B4A),
    episodeBadgeBackground = Color(0xFFF9F0EE),
    error = Color(0xFFE24B4A),
    avatarBackground = Color(0xFFD4B8B2)
)

val DarkCharacterDetailsColors = CharacterDetailsColors(
    background = Color(0xFF1A1210),
    heroBackground = Color(0xFF2E1F1C),
    cardBackground = Color(0xFF251815),
    textPrimary = Color(0xFFF2E4DF),
    textMuted = Color(0xFFAB9088),
    statusPillBackground = Color(0xFFF2E4DF),
    statusPillText = Color(0xFF1A1210),
    statusAlive = Color(0xFF4BBF68),
    statusDead = Color(0xFFE05554),
    episodeBadgeBackground = Color(0xFF2E1F1C),
    error = Color(0xFFE05554),
    avatarBackground = Color(0xFF4A3330)
)
