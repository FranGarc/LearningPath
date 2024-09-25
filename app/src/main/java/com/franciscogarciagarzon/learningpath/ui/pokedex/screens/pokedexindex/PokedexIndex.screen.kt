package com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokedexindex

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.PokemonListUi
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.StateWrapper
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.toPokemonListUi
import com.franciscogarciagarzon.learningpath.ui.common.components.ErrorDialog
import com.franciscogarciagarzon.learningpath.ui.common.components.LoadingIndicator
import com.franciscogarciagarzon.learningpath.ui.common.navigation.BottomNavBar
import com.franciscogarciagarzon.learningpath.ui.common.theme.LearningPathTheme


@Composable
fun PokedexIndexPage(
    showPokemonArticle: (pokemonName: String) -> Unit = {},
    homeNavigation: () -> Unit = {},
    favNavigation: () -> Unit = {},
    viewModel: PokedexIndexViewModel = hiltViewModel()
) {
    val pokedexIndexListStateFlow by viewModel.uiState.collectAsState()
    Log.d("PokedexIndexList.screen", "pokedexIndexListFlow: $pokedexIndexListStateFlow")
    lateinit var pokedexIndexList: PokemonListUi
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (pokedexIndexListStateFlow) {
            is StateWrapper.Success -> {
                Log.d("PokedexIndexList.screen", "state: Success")
                pokedexIndexList = (pokedexIndexListStateFlow as StateWrapper.Success<PokedexIndexList>).value.toPokemonListUi()
                Screen(
                    showPokemonArticle,
                    homeNavigation,
                    favNavigation,
                    pokedexIndexListData = pokedexIndexList
                )
            }

            is StateWrapper.Error -> {
                val errorMessage = (pokedexIndexListStateFlow as StateWrapper.Error).message
                Log.w("PokedexIndexList.screen", "state: Error message $errorMessage")
                ErrorDialog(
                    message = errorMessage,
                    onDismissRequest = viewModel::resetUiState,
                    onRetry = { viewModel.getPokedexIndexList() }
                )

            }

            is StateWrapper.Loading -> {
                Log.d("PokedexIndexList.screen", "state: Loading")
                LoadingIndicator()
            }

            is StateWrapper.Nothing -> {
                Log.d("PokedexIndexList.screen", "state: Nothing")
                Screen(
                    showPokemonArticle,
                    homeNavigation,
                    favNavigation,
                    pokedexIndexListData = PokemonListUi(emptyList())
                )
            }

        }
    }


}

@Composable
fun Screen(
    showPokemonArticle: (pokemonName: String) -> Unit = {},
    homeNavigation: () -> Unit = {},
    favNavigation: () -> Unit = {},
    pokedexIndexListData: PokemonListUi
) {
    LearningPathTheme {
        Scaffold(
            content = { innerPadding ->
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PokedexIndexGrid(
                        pokemonListData = pokedexIndexListData,
                        innerPadding = innerPadding,
                        showPokemonDetail = showPokemonArticle
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
        Screen(pokedexIndexListData = MockDataSource().getMockPokedexIndexList().toPokemonListUi())
    }
}