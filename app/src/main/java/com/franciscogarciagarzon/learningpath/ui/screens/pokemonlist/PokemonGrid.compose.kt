package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonListUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonListUi
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme

@Composable
fun PokedexGrid(
    pokemonListData: PokemonListUi,
    innerPadding: PaddingValues,
    showPokemonDetail: (pokemonName: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(pokemonListData.pokemons.size) { index ->
            val pokemon = pokemonListData.pokemons[index]
            PokemonListCard(pokemon = pokemon, clickAction = {
                Log.d("ListItem", "clicked on ${pokemon.name}")
                showPokemonDetail(pokemon.name)
            })

        }

    }
}

@Preview(name = "NEXUS_6", device = Devices.NEXUS_6, showSystemUi = true)
@Composable
fun PokemonList1Preview() {
    LearningPathTheme {
        PokedexGrid(
            pokemonListData = MockDataSource().getPokemonListDto().toPokemonListUi(),
            innerPadding = PaddingValues(1.dp),
            showPokemonDetail = {}

        )
    }
}