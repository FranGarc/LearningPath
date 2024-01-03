package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.ui.model.StatUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel
import com.franciscogarciagarzon.learningpath.ui.screens.components.StatIndicator

@Preview(name = "PIXEL Dark", device = Devices.PIXEL, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatRow(stat: StatUi = StatUi(name = "Sp. Attack", value = 100), modifier: Modifier = Modifier, maxValue: Int = 255) {
    Log.d("StatRow", "stat: $stat")
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            horizontalArrangement = Arrangement.End, modifier = modifier.weight(0.5f)
        ) {
            RegularLabel(text = stat.name, modifier = modifier.weight(0.6f), textAlignment = TextAlign.End)

            RegularLabel(text = stat.value.toString(), modifier = modifier.weight(0.2f), textAlignment = TextAlign.End)

        }
        Row(
            horizontalArrangement = Arrangement.Start, modifier = modifier.weight(0.6f), verticalAlignment = Alignment.CenterVertically
        ) {
            val barColor = if (stat.value > 77) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.inversePrimary
            }
            StatIndicator(
                statValue = stat.value,
                modifier = Modifier
                    .weight(0.8f)
                    .padding(end = 5.dp, start = 5.dp),
                color = barColor
            )

            RegularLabel(
                text = maxValue.toString(), modifier = Modifier
                    .weight(0.2f), textAlignment = TextAlign.End
            )
        }
    }
}