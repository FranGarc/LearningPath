package com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokemonarticle

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
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.PokemonArticleUi
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.toPokemonArticleUi
import com.franciscogarciagarzon.learningpath.ui.common.components.RegularLabel

@Composable
fun PokemonTabLayout(
    onClick: (PokemonArticleUserEvent) -> Unit,
    tabs: List<String>,
    tabIndex: Int,
    updateTabIndexBasedOnSwipe: (PokemonArticleUserEvent) -> Unit,
    pokemonArticleUi: PokemonArticleUi
) {
    Column(
        modifier = Modifier.fillMaxWidth()

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
                    onClick = { onClick(PokemonArticleUserEvent.OnClickedTab(index)) },
                )
            }
        }

        when (tabIndex) {
            0 -> PokemonInfoAbout(
                pokemonArticleUi = pokemonArticleUi,
                updateTabIndexBasedOnSwipe = updateTabIndexBasedOnSwipe,
            )

            1 -> PokemonInfoStats(
                stats = pokemonArticleUi.stats,
                updateTabIndexBasedOnSwipe = updateTabIndexBasedOnSwipe,
            )
        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabLayoutPreview() {
    PokemonTabLayout(tabs = listOf("About", "Base Stats"), pokemonArticleUi = MockDataSource().getPokemonArticle().toPokemonArticleUi(), tabIndex = 0, onClick = {}, updateTabIndexBasedOnSwipe = {})
}