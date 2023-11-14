package com.franciscogarciagarzon.learningpath.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegularLabel(text: String, modifier: Modifier = Modifier, textAlignment: TextAlign = TextAlign.Justify) {
    Text(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp),
        text = text.capitalize(locale = Locale.current),
        textAlign = textAlignment,
        color = Color.Black,
        fontSize = 18.sp,
    )
}