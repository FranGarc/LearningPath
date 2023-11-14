package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.franciscogarciagarzon.learningpath.ui.model.StatUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.BoldLabel
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel
import com.franciscogarciagarzon.learningpath.ui.screens.components.StatIndicator

@Preview
@Composable
fun StatRow(stat: StatUi = StatUi(name = "HP"), modifier: Modifier = Modifier, maxValue: Int = 255) {
    Log.d("StatRow", "stat: $stat")
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            horizontalArrangement = Arrangement.End, modifier = modifier.weight(0.4f)
        ) {
            BoldLabel(
                text = stat.name, modifier = modifier.weight(0.7f), textAlignment = TextAlign.End
            )

            RegularLabel(text = stat.value.toString(), modifier = modifier.weight(0.3f))

        }
        Row(
            horizontalArrangement = Arrangement.Start, modifier = modifier.weight(0.6f), verticalAlignment = Alignment.CenterVertically
        ) {
            StatIndicator(stat.value, modifier = Modifier.weight(0.7f))
            RegularLabel(text = maxValue.toString(), modifier = Modifier.weight(0.3f))
        }
    }
}