package com.franciscogarciagarzon.learningpath.ui.extensions

import androidx.compose.ui.graphics.Color

fun Color.lightenBy(amount: Float): Color {
    val alpha = this.alpha
    val red = this.red
    val green = this.green
    val blue = this.blue

    return Color(
        alpha = alpha,
        red = (red * (1 - amount) + amount),
        green = (green * (1 - amount) + amount),
        blue = (blue * (1 - amount) + amount)
    )
}