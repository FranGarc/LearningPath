package com.franciscogarciagarzon.learningpath.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.AbilityUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonDetailUi

@Composable
fun AbilityComponent(
    modifier: Modifier,
    ability: AbilityUi,
    gradientBrush: Brush,
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(gradientBrush),
        contentAlignment = Alignment.Center,

    ) {
        DoubleLineLabel(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            text = ability.printableName(),
            textAlignment = TextAlign.Center,
            modifier = modifier
//                .padding(start = 2.dp, end = 2.dp, top = 10.dp, bottom = 10.dp)
                .padding(2.dp)
        )
    }
}

@Preview
@Composable
fun AbilityComponentPreview() {
    val pokemon = MockDataSource().getPokemonDetailDto().toPokemonDetailUi()
    val typeColorsGradientForAbilities = Brush.horizontalGradient(
        colors = pokemon.typeColorsForAbilities(),
        startX = 0f, endX = 250f
    )
    AbilityComponent(
        modifier = Modifier,
        ability = pokemon.abilities.first(), gradientBrush = typeColorsGradientForAbilities
    )
}