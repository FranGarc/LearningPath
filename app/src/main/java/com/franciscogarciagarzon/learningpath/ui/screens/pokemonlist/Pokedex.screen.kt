package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.ui.model.PokemonListUi
import com.franciscogarciagarzon.learningpath.ui.model.StateWrapper
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonListUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.ErrorDialog
import com.franciscogarciagarzon.learningpath.ui.screens.components.LoadingIndicator
import com.franciscogarciagarzon.learningpath.ui.screens.navigation.BottomNavBar
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexList(
    showPokemonDetail: (pokemonName: String) -> Unit = {},
    homeNavigation: () -> Unit = {},
    favNavigation: () -> Unit = {},
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonListStateFlow by viewModel.uiState.collectAsState()
    Log.d("PokemonList.screen", "pokemonListFlow: $pokemonListStateFlow")
    lateinit var pokemonList: PokemonListUi
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (pokemonListStateFlow) {
            is StateWrapper.Success -> {
                Log.d("PokemonList.screen", "state: Success")
                pokemonList = (pokemonListStateFlow as StateWrapper.Success<PokemonListDto>).value.toPokemonListUi()
                Screen(
                    showPokemonDetail,
                    homeNavigation,
                    favNavigation,
                    pokemonListData = pokemonList
                )
            }

            is StateWrapper.Error -> {
                val errorMessage = (pokemonListStateFlow as StateWrapper.Error).message
                Log.w("PokemonList.screen", "state: Error message $errorMessage")
                ErrorDialog(message = errorMessage, onDismissRequest = viewModel::resetUiStatae, onRetry = { viewModel.getPokemonList() })

            }

            is StateWrapper.Loading -> {
                Log.d("PokemonList.screen", "state: Loading")
                LoadingIndicator()
            }

            is StateWrapper.Nothing -> {
                Log.d("PokemonList.screen", "state: Nothing")
                Screen(
                    showPokemonDetail,
                    homeNavigation,
                    favNavigation,
                    pokemonListData = PokemonListUi(emptyList())
                )
            }

        }
    }


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

@Preview(
    name = "NEXUS_6", device = Devices.NEXUS_6,
    showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PokemonListPreview() {
    LearningPathTheme {
        Screen(pokemonListData = MockDataSource().getPokemonListDto().toPokemonListUi())
    }
}