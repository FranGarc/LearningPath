package com.franciscogarciagarzon.learningpath.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DoubleLineLabel(
    text: String,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    var height by remember { mutableStateOf(1.dp) }
    val density = LocalDensity.current
    Text(
        modifier = modifier
            .wrapContentHeight()
            .padding(start = 2.dp, end = 2.dp, top = height, bottom = height),
        text = text.capitalize(locale = Locale.current),
        color = color,
        onTextLayout = { textLayoutResult ->
            val newHeight = with(density) { textLayoutResult.size.height.toDp() }
            if (textLayoutResult.lineCount == 1) {
                height = newHeight.times(0.5f)
            }
        },
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlignment,
    )
}

@Composable
fun RegularLabel(
    text: String,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Justify,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
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
    DoubleLineLabel(text = "TestingText", modifier = Modifier.background(Color.Gray))
}