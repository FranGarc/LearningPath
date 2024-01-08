package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.ui.model.StatUi
import com.franciscogarciagarzon.learningpath.ui.model.StatsUi

@Composable
fun PokemonInfoStats(
    stats: StatsUi,
    updateTabIndexBasedOnSwipe: (Boolean) -> Unit,
) {
    var isSwipeToTheLeft by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(onDelta = { delta ->
        isSwipeToTheLeft = delta > 0
    })

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .draggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                onDragStarted = {
                    Log.d("PokemonInfoStats", "onDragStarted")
                },
                onDragStopped = {
                    Log.d("PokemonInfoStats", "onDragStopped  isSwipeToTheLeft: $isSwipeToTheLeft")
                    updateTabIndexBasedOnSwipe(isSwipeToTheLeft)
                }
            )
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatRow(stat = stats.hp, modifier = Modifier.rotate(0f))
        StatRow(stat = stats.attack, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.defense, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.speed, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.specialAttack, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.specialDefense, modifier = Modifier.rotate(-0f))
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonInfoStatsPreview() {
    PokemonInfoStats(
        updateTabIndexBasedOnSwipe = {},
        stats = StatsUi(
            attack = StatUi("attack", 10),
            defense = StatUi("defense", 10),
            hp = StatUi("hp", 10),
            specialAttack = StatUi("sp. Attack", 10),
            specialDefense = StatUi("sp. Defense", 10),
            speed = StatUi("speed", 10),
        )
    )
}