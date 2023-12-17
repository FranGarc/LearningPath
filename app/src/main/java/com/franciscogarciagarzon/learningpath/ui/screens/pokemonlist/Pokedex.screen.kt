package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonListUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonListUi
import com.franciscogarciagarzon.learningpath.ui.screens.navigation.BottomNavBar
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@Composable
fun PokedexList(
    showPokemonDetail: (pokemonName: String) -> Unit = {},
    homeNavigation: () -> Unit = {},
    favNavigation: () -> Unit = {},
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonListFlow by viewModel.uiState.collectAsState()
    Log.d("PokemonList.screen", "pokemonListFlow: $pokemonListFlow")
    Screen(
        showPokemonDetail,
        homeNavigation,
        favNavigation,
        pokemonListData = pokemonListFlow.toPokemonListUi()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    showPokemonDetail: (pokemonName: String) -> Unit = {},
    homeNavigation: () -> Unit = {},
    favNavigation: () -> Unit = {},
    pokemonListData: PokemonListUi
) {
    LearningPathTheme {
        Scaffold(

            content = { innerPadding ->
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    PokedexList(
                    PokedexGrid(
                        pokemonListData = pokemonListData, innerPadding = innerPadding, showPokemonDetail = showPokemonDetail
                    )
                }
            },
            bottomBar = {
                BottomNavBar(
                    homeNavigation = homeNavigation,
                    favNavigation = favNavigation,
                )
            },
        )
    }
}

@Preview(name = "NEXUS_6", device = Devices.NEXUS_6, showSystemUi = true)
@Composable
fun PokemonListPreview() {
    LearningPathTheme {
        Screen(pokemonListData = MockDataSource().getPokemonListDto().toPokemonListUi())
    }
}