package com.franciscogarciagarzon.learningpath.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val darkColorScheme = darkColorScheme(
    primary = Color.Yellow,
    primaryContainer = Color.Yellow,
    background = Color.Blue,
    onBackground = Color.White,
    surface = Color(0xFF303030),
    onSurface = Color.White
)

private val lightColorScheme = lightColorScheme(
    primary = Color.Blue,
    primaryContainer = LightBlue,
    onPrimary = Color.DarkGray,
    background = LightBlue,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }

//object LearningPathTheme {
//    /**
//     * Retrieves the current [ColorScheme] at the call site's position in the hierarchy.
//     */
//    val colorScheme: ColorScheme
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalColorScheme.current
//
//    /**
//     * Retrieves the current [Typography] at the call site's position in the hierarchy.
//     */
//    val typography: Typography
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalTypography.current
//
//    /**
//     * Retrieves the current [Shapes] at the call site's position in the hierarchy.
//     */
//    val shapes: Shapes
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalShapes.current
//}

@Composable
fun LearningPathTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}