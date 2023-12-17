package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.ui.model.PokemonListUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonListUi
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme

@Composable
fun PokedexList(
    pokemonListData: PokemonListUi,
    innerPadding: PaddingValues,
    showPokemonDetail: (pokemonName: String) -> Unit
) {
    LazyColumn(
        modifier = androidx.compose.ui.Modifier.padding(innerPadding),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
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
fun PokedexList1Preview() {
    LearningPathTheme {
        PokedexList(
            pokemonListData = MockDataSource().getPokemonListDto().toPokemonListUi(),
            innerPadding = PaddingValues(1.dp),
            showPokemonDetail = {}

        )
    }
}