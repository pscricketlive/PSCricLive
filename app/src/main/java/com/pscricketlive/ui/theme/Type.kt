package com.pscricketlive.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Text styles used across the app.
 * Change sizes/weights here — screens should never set font size inline.
 */
val PSTypography = Typography(
    headlineLarge = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 32.sp),  // e.g. "112/3"
    titleLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),          // match title
    titleMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),     // player names
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.Medium, fontSize = 12.sp),        // "SCORE & BROADCAST" style labels
)
