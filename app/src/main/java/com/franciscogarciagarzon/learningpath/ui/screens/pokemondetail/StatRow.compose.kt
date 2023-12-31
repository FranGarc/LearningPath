package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.franciscogarciagarzon.learningpath.data.model.Stat
import com.franciscogarciagarzon.learningpath.ui.screens.components.BoldLabel
import com.franciscogarciagarzon.learningpath.ui.screens.components.StatIndicator


@Composable
fun StatRow(stat: Stat) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(0.5f)
        ) {
            BoldLabel(
                text = stat.name,
                modifier = Modifier.weight(0.5f),
                textAlignment = TextAlign.End
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.weight(0.5f)
        ) {
            StatIndicator(stat.value)
        }
    }
}