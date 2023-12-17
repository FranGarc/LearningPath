package com.franciscogarciagarzon.learningpath.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegularLabel(text: String, modifier: Modifier = Modifier, textAlignment: TextAlign = TextAlign.Justify, color: Color = MaterialTheme.colorScheme.onPrimaryContainer) {
    Text(
        modifier = modifier
            .padding(start = 2.dp, end = 2.dp),
        text = text.capitalize(locale = Locale.current),
        textAlign = textAlignment,
        color = color,
        style = MaterialTheme.typography.bodySmall,
    )
}

@Preview(name = "PIXEL Dark", device = Devices.PIXEL, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegularLabelPreview() {
    RegularLabel(text = "TestingText")
}