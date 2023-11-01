package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.franciscogarciagarzon.learningpath.ui.screens.navigation.TopNavBar
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetail(
    id: String, navigateUp: () -> Unit = {}, viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val pokemonDetail by viewModel.uiState.collectAsState()
    viewModel.getPokemonDetail(pokemonId = id)
    LearningPathTheme {
        Scaffold(topBar = {
            TopNavBar(
                title = pokemonDetail.name, modifier = Modifier, upNavigation = navigateUp
            )
        }, content = { innerPadding ->
            Log.d("PokemonDetailScreen", "Composable pokemonDetail: $pokemonDetail")
            PokemonDetail(pokemonDetail, innerPadding)
        })

    }

}
