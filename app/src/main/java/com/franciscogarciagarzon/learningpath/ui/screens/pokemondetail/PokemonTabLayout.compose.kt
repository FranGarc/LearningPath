package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel

@Composable
fun PokemonTabLayout(
//    viewModel: PokemonDetailViewModel = hiltViewModel(),
    onClick: (Int) -> Unit,
    tabs: List<String>,
    tabIndex: Int,
    pokemonDetailUi: PokemonDetailUi
) {


//    val tabIndex = viewModel.tabIndex.collectAsState()
    Surface {

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = tabIndex,

            ) {
//            viewModel.tabs.forEachIndexed { index, title ->
            tabs.forEachIndexed { index, title ->
                Log.d("PokemonTabLayout", "title: $title || index: $index")
                Tab(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                    text = { RegularLabel(title) },
                    selected = tabIndex == index,
                    onClick = { onClick(index) },
//                    onClick = { viewModel.updateTabIndex(index) },
//                    icon = {
//                        when (index) {
//                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
//                            1 -> Icon(imageVector = Icons.Default.Info, contentDescription = null)
//                            2 -> Icon(imageVector = Icons.Default.Settings, contentDescription = null)
//                        }
//                    }
                )
            }
        }

        when (tabIndex) {
            0 -> PokemonInfoAbout(pokemonDetailUi = pokemonDetailUi)
            1 -> PokemonInfoStats(stats = pokemonDetailUi.stats)
        }
    }

}

@Composable
fun CustomTabIndicator(selectedTabIndex: Int) {

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabLayoutPreview() {
    PokemonTabLayout(
        tabs = listOf("About", "Base Stats"),
        pokemonDetailUi = MockDataSource().getPokemonDetailDto().toPokemonDetailUi(),
        tabIndex = 0,
        onClick = {}
    )
}