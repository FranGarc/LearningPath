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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.franciscogarciagarzon.learningpath.ui.screens.navigation.BottomNavBar
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PokemonList(
    showPokemonDetail: (pokemonName: String) -> Unit = {}, homeNavigation: () -> Unit = {}, favNavigation: () -> Unit = {}
) {

    val viewModel = viewModel<PokemonListViewModel>()
    val pokemonListFlow by viewModel.uiState.collectAsState()
    Log.d("PokemonList.screen", "pokemonListFlow: $pokemonListFlow")
    LearningPathTheme {
        Scaffold(

            content = { innerPadding ->
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PokemonList(
                        pokemonList = pokemonListFlow, innerPadding = innerPadding, showPokemonDetail = showPokemonDetail
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


        // A surface container using the 'background' color from the theme

    }

}


@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    LearningPathTheme {
//        PokemonList()
    }
}