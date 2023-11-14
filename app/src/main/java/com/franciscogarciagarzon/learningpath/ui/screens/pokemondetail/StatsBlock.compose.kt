package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.ui.model.StatUi
import com.franciscogarciagarzon.learningpath.ui.model.StatsUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.BoldLabel30

@Composable
fun StatBlock(
    stats: StatsUi
) {

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
//        contentAlignment = Alignment.Center
    ) {
        BoldLabel30(
            text = "Stats", textAlignment = TextAlign.Center, modifier = Modifier.padding(top = 10.dp)
        )
        StatRow(stat = stats.hp, modifier = Modifier.rotate(0f))
        StatRow(stat = stats.attack, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.defense, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.speed, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.specialAttack, modifier = Modifier.rotate(-0f))
        StatRow(stat = stats.specialDefense, modifier = Modifier.rotate(-0f))
    }

}

@Preview
@Composable
fun StatBlock() {
    StatBlock(
        stats = StatsUi(
            attack = StatUi("attack", 10),
            defense = StatUi("defense", 10),
            hp = StatUi("hp", 10),
            specialAttack = StatUi("specialAttack", 10),
            specialDefense = StatUi("specialDefense", 10),
            speed = StatUi("speed", 10),
        )
    )
}