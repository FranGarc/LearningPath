package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel

@Composable
fun PokemonTabLayout(
    onClick: (Int) -> Unit,
    tabs: List<String>,
    tabIndex: Int,
    updateTabIndexBasedOnSwipe: (Boolean) -> Unit,
    pokemonDetailUi: PokemonDetailUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        TabRow(
            selectedTabIndex = tabIndex,

            ) {
            tabs.forEachIndexed { index, title ->
                Log.d("PokemonTabLayout", "title: $title || index: $index")
                Tab(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                    text = { RegularLabel(title) },
                    selected = tabIndex == index,
                    onClick = { onClick(index) },
                )
            }
        }

        when (tabIndex) {
            0 -> PokemonInfoAbout(
                pokemonDetailUi = pokemonDetailUi,
                updateTabIndexBasedOnSwipe = updateTabIndexBasedOnSwipe,
            )

            1 -> PokemonInfoStats(
                stats = pokemonDetailUi.stats,
                updateTabIndexBasedOnSwipe = updateTabIndexBasedOnSwipe,
            )
        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabLayoutPreview() {
    PokemonTabLayout(
        tabs = listOf("About", "Base Stats"),
        pokemonDetailUi = MockDataSource().getPokemonDetailDto().toPokemonDetailUi(),
        tabIndex = 0,
        onClick = {},
        updateTabIndexBasedOnSwipe = {}
    )
}