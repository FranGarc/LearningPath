package com.franciscogarciagarzon.learningpath.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoldLabel(text: String, modifier: Modifier = Modifier, textAlignment: TextAlign = TextAlign.Start, color: Color = MaterialTheme.colorScheme.onPrimaryContainer) {
    Text(
        modifier = modifier
            .padding(5.dp, end = 5.dp),
        text = text.capitalize(locale = Locale.current),
        color = color,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlignment
    )
}

@Composable
fun BoldLabel40(text: String, modifier: Modifier = Modifier, textAlignment: TextAlign = TextAlign.Justify, color: Color = MaterialTheme.colorScheme.onPrimaryContainer) {
    Text(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp),
        text = text.capitalize(locale = Locale.current),
        textAlign = textAlignment,
        color = color,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun BoldLabel30(text: String, modifier: Modifier = Modifier, textAlignment: TextAlign = TextAlign.Justify, color: Color = MaterialTheme.colorScheme.onPrimaryContainer) {
    Text(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp),
        text = text.capitalize(locale = Locale.current),
        color = color,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlignment
    )
}

@Composable
fun BoldLabel20(text: String, modifier: Modifier = Modifier, textAlignment: TextAlign = TextAlign.Justify, color: Color = MaterialTheme.colorScheme.onPrimaryContainer) {
    Text(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp),
        text = text.capitalize(locale = Locale.current),
        color = color,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlignment
    )
}
