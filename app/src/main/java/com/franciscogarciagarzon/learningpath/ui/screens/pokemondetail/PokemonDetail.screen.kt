package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.screens.navigation.TopNavBar
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@Composable
fun PokemonDetail(
    id: String, navigateUp: () -> Unit = {}, viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val pokemonDetail by viewModel.uiState.collectAsState()
    viewModel.getPokemonDetail(pokemonId = id)
    val tabs = viewModel.tabs
    val tabIndex = viewModel.tabIndex.collectAsState()
    val onClickedTab: (Int) -> Unit = viewModel::updateTabIndex
    Screen(
        pokemonDetail = pokemonDetail,
        navigateUp = navigateUp,
        tabs = tabs,
        tabIndex = tabIndex.value,
        onClickedTab = onClickedTab

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    pokemonDetail: PokemonDetailUi,
    navigateUp: () -> Unit = {},
    tabs: List<String>,
    tabIndex: Int,
    onClickedTab: (Int) -> Unit
) {
    LearningPathTheme {
        Scaffold(topBar = {
            TopNavBar(
                title = pokemonDetail.name, modifier = Modifier, upNavigation = navigateUp
            )
        }, content = { innerPadding ->
            Log.d("PokemonDetailScreen", "Composable pokemonDetail: $pokemonDetail")
//            PokemonDetail(pokemonDetail, innerPadding)
            if (pokemonDetail.isLoaded())
                PokemonInfo(
                    pokemonDetail,
                    innerPadding,
                    tabs = tabs,
                    tabIndex = tabIndex,
                    onClickedTab = onClickedTab
                )
        })
    }
}

@Preview(name = "PIXEL Dark", device = Devices.PIXEL, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "PIXEL", device = Devices.PIXEL, showSystemUi = true)
@Preview(name = "PIXEL2", device = Devices.PIXEL_2, showSystemUi = true)
@Preview(name = "PIXEL3", device = Devices.PIXEL_3, showSystemUi = true)
//@Preview(name = "PIXEL_XL", device = Devices.NEXUS_10, showSystemUi = true)
@Preview(name = "NEXUS_6", device = Devices.NEXUS_6, showSystemUi = true)
@Composable
fun PreviewDetail() {
    Screen(
        pokemonDetail = MockDataSource().getPokemonDetailDto().toPokemonDetailUi(),
        navigateUp = {},
        tabs = listOf("About", "Base Stats"),
        tabIndex = 1,
        onClickedTab = { }
    )
}