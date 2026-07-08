package com.pscricketlive.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Brand palette — the ONLY place raw hex values should appear.
 * Every screen/component references these names, never a hex code directly.
 * To rebrand later: change values here only.
 */

// Core gold family (from PS logo)
val GoldLight = Color(0xFFD9B65C)
val GoldPrimary = Color(0xFFB8860B)
val GoldDark = Color(0xFF8A6412)
val GoldText = Color(0xFFE8C875)

// Neutrals
val CreamBackground = Color(0xFFFBF6E9)
val CardWhite = Color(0xFFFFFFFF)
val InkBlack = Color(0xFF2A2A2A)
val NavBarBlack = Color(0xFF1A1A1A)

// Semantic (status/feedback) — kept separate from brand palette
// so brand refresh never accidentally breaks meaning (e.g. red = wicket)
val WicketRed = Color(0xFFC0392B)
val BoundaryGreen = Color(0xFF2E7D32)
val SixPurple = Color(0xFF7B2CBF)
