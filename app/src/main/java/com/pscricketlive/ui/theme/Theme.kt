package com.pscricketlive.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Wraps the whole app. Every screen should be rendered inside PSCricketLiveTheme { ... }
 * so it automatically gets brand colors + typography — no per-screen setup needed.
 */
private val LightColors = lightColorScheme(
    primary = GoldPrimary,
    onPrimary = CardWhite,
    secondary = GoldDark,
    background = CreamBackground,
    surface = CardWhite,
    onBackground = InkBlack,
    onSurface = InkBlack,
    error = WicketRed,
)

private val DarkColors = darkColorScheme(
    primary = GoldLight,
    onPrimary = InkBlack,
    secondary = GoldDark,
    background = NavBarBlack,
    surface = Color(0xFF242424),
    onBackground = CardWhite,
    onSurface = CardWhite,
    error = WicketRed,
)

@Composable
fun PSCricketLiveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = PSTypography,
        content = content
    )
}
