package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.AbilityComponent
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel

@Composable
fun PokemonInfoAbout(
    pokemonDetailUi: PokemonDetailUi,
    updateTabIndexBasedOnSwipe: (Boolean) -> Unit,
) {

    var isSwipeToTheLeft by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(onDelta = { delta ->
        isSwipeToTheLeft = delta > 0
    })

    val paddingContainerTop = 10.dp
    val paddingRowTop = 15.dp
    val paddingBetweenColumns = 45.dp
    val paddingStart = 10.dp

    val typeColors: List<Color> = pokemonDetailUi.typeColorsForAbilities()


    val typeColorsGradientForAbilities = Brush.horizontalGradient(
        colors = typeColors,
        startX = 0f, endX = 250f
    )
    Column(
        modifier = Modifier
            .draggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                onDragStarted = {
                    Log.d("PokemonInfoAbout", "onDragStarted")
                },
                onDragStopped = {
                    Log.d("PokemonInfoAbout", "onDragStopped isSwipeToTheLeft: $isSwipeToTheLeft")
                    updateTabIndexBasedOnSwipe(isSwipeToTheLeft)
                }
            )
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingContainerTop)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = paddingStart)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = paddingRowTop)
                ) {
                    RegularLabel(text = "Height")
                }
                Row(
                    modifier = Modifier
                        .padding(top = paddingRowTop)
                ) {
                    RegularLabel(text = "Weight")
                }

            }
            Column(modifier = Modifier.padding(start = paddingBetweenColumns)) {
                Row(
                    modifier = Modifier
                        .padding(top = paddingRowTop)
                ) {
                    RegularLabel(text = pokemonDetailUi.printableHeight())
                }
                Row(
                    modifier = Modifier
                        .padding(top = paddingRowTop)
                ) {
                    RegularLabel(text = pokemonDetailUi.printableWeight())
                }

            }
        }

        Row(
            modifier = Modifier
                .padding(top = 30.dp, start = paddingStart, end = paddingStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegularLabel(text = "Ability")
            Spacer(modifier = Modifier.padding(start = paddingBetweenColumns))
            val abilitiesAmount = pokemonDetailUi.abilities.size
            val abilitiesWeight: Float = when (abilitiesAmount) {
                1 -> 1f
                2 -> 0.5f
                3 -> 0.3f
                else -> 1f
            }
            pokemonDetailUi.abilities.forEach { abilityUi ->
                AbilityComponent(
                    modifier = Modifier.weight(abilitiesWeight),
                    ability = abilityUi,
                    gradientBrush = typeColorsGradientForAbilities
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PokemonInfoAboutPreview() {
    PokemonInfoAbout(
        updateTabIndexBasedOnSwipe = {},
        pokemonDetailUi = MockDataSource().getPokemonDetailDto().toPokemonDetailUi(),

        )
}