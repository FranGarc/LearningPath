package com.franciscogarciagarzon.learningpath.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.ui.extensions.capitalizeLP
import com.franciscogarciagarzon.learningpath.ui.extensions.lightenBy
import com.franciscogarciagarzon.learningpath.ui.model.TypeUi

@Composable

fun TypeComponent(pokemonType: TypeUi) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = pokemonType.color.lightenBy(0.45f))
    ) {
        RegularLabel(
            color = Color.Black,
            text = pokemonType.type.name.capitalizeLP(),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun previewTypeComponent() {
    TypeComponent(pokemonType = TypeUi.Electric)
}