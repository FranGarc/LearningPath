package com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokemonarticle

import android.content.res.Configuration
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.PokemonArticleUi
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.StateWrapper
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.toPokemonArticleUi
import com.franciscogarciagarzon.learningpath.ui.common.components.ErrorDialog
import com.franciscogarciagarzon.learningpath.ui.common.components.LoadingIndicator
import com.franciscogarciagarzon.learningpath.ui.common.navigation.TopNavBar
import com.franciscogarciagarzon.learningpath.ui.common.theme.LearningPathTheme


@Composable
fun PokemonArticle(
    id: String, navigateUp: () -> Unit = {}, viewModel: PokemonArticleViewModel = hiltViewModel()
) {

    val pokemonArticleStateFlow by viewModel.uiState.collectAsState()
    lateinit var pokemonDetail: PokemonArticleUi
    DisposableEffect(key1 = Unit) {
        Log.d("PokemonArticle.screen", "DisposableEffect")
        viewModel.getPokemonDetail(pokemonId = id)
        onDispose {
            Log.d("PokemonArticle.screen", "DisposableEffect onDispose")
        }
    }


    val tabs = viewModel.tabs
    val tabIndex = viewModel.tabIndex.collectAsState()
    val onClickedTab: (PokemonArticleUserEvent) -> Unit = viewModel::onEvent


    when (pokemonArticleStateFlow) {
        is StateWrapper.Success -> {
            pokemonDetail = (pokemonArticleStateFlow as StateWrapper.Success<PokemonArticle>).value.toPokemonArticleUi()
            Screen(
                pokemonArticle = pokemonDetail,
                navigateUp = navigateUp,
                tabs = tabs,
                tabIndex = tabIndex.value,
                updateTabIndexBasedOnSwipe = viewModel::onEvent, onClickedTab = onClickedTab
            )
        }

        is StateWrapper.Error -> {
            val errorMessage = (pokemonArticleStateFlow as StateWrapper.Error).message
            Log.w("PokemonArticle.screen", "state: Error message $errorMessage")
            ErrorDialog(message = errorMessage, onDismissRequest = viewModel::resetUiStatae, onRetry = { viewModel.getPokemonDetail(id) })

        }

        is StateWrapper.Loading -> {
            Log.d("PokemonArticle.screen", "state: Loading")
            LoadingIndicator()
        }

        is StateWrapper.Nothing -> {
            Log.d("PokemonArticle.screen", "state: Nothing")
            Screen(
                pokemonArticle = PokemonArticleUi(),
                navigateUp = navigateUp,
                tabs = tabs,
                tabIndex = tabIndex.value,
                updateTabIndexBasedOnSwipe = viewModel::onEvent,
                onClickedTab = onClickedTab
            )
        }

    }


}

@Composable
fun Screen(
    pokemonArticle: PokemonArticleUi,
    navigateUp: () -> Unit = {},
    tabs: List<String>,
    tabIndex: Int,
    onClickedTab: (PokemonArticleUserEvent) -> Unit,
    updateTabIndexBasedOnSwipe: (PokemonArticleUserEvent) -> Unit,
) {
    LearningPathTheme {
        Scaffold(topBar = {
            TopNavBar(
                title = pokemonArticle.name, modifier = Modifier, upNavigation = navigateUp
            )
        }, content = { innerPadding ->
            Log.d("PokemonDetailScreen", "Composable pokemonDetail: $pokemonArticle")
            if (pokemonArticle.isLoaded()) PokemonInfo(
                pokemonArticle, innerPadding, tabs = tabs, tabIndex = tabIndex,
                updateTabIndexBasedOnSwipe = updateTabIndexBasedOnSwipe,
                onClickedTab = onClickedTab
            )
        })
    }
}

@Preview(name = "PIXEL Dark", device = Devices.PIXEL, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "PIXEL", device = Devices.PIXEL, showSystemUi = true)
@Preview(name = "PIXEL2", device = Devices.PIXEL_2, showSystemUi = true)
@Preview(name = "PIXEL3", device = Devices.PIXEL_3, showSystemUi = true)
@Preview(name = "NEXUS_6", device = Devices.NEXUS_6, showSystemUi = true)
@Composable
fun PreviewDetail() {
//    Screen(pokemonDetail = MockDataSource().getPokemonDetailDto().toPokemonDetailUi(), navigateUp = {}, tabs = listOf("About", "Base Stats"), tabIndex = 1, updateTabIndexBasedOnSwipe = {}, onClickedTab = { })
}