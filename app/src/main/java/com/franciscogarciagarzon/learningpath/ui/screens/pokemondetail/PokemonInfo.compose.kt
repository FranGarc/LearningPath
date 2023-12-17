package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

//import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi.toPokemonDetailUi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.R
import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.BoldLabel20
import com.franciscogarciagarzon.learningpath.ui.screens.components.RemoteImage
import com.franciscogarciagarzon.learningpath.ui.screens.components.TypeComponent

@Composable
fun PokemonInfo(
    pokemonDetail: PokemonDetailUi,
    innerPadding: PaddingValues,
    onClickedTab: (Int) -> Unit,
    tabs: List<String>,
    tabIndex: Int,
) {
    val pokemon: PokemonDetailUi = pokemonDetail
    val gradient = Brush.verticalGradient(
        colors = pokemon.typeColors(),
        startY = 200f, endY = 800f
    )

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Spacer(modifier = Modifier.padding(top = 60.dp))
                val splittedNameParts = pokemon.name.split("-").toMutableList()
                Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    BoldLabel20(text = splittedNameParts.first(), textAlignment = TextAlign.Center)
                    BoldLabel20(text = pokemon.printableId(), textAlignment = TextAlign.End, modifier = Modifier.weight(1f))


                }
                splittedNameParts.removeFirst()
                Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    BoldLabel20(text = splittedNameParts.joinToString(separator = " "), textAlignment = TextAlign.Center)
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.weight(0.1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val types = pokemon.types
                    TypeComponent(types.first())
//                Spacer(modifier = Modifier.weight(0.1f))
                    if (types.size > 1) {
                        TypeComponent(types.last())
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Column(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                                .background(color = MaterialTheme.colorScheme.background)

                        ) {

                        }
                    }
                    RemoteImage(
//                        imageUrl = pokemonDetail.sprites.frontDefault,
                        imageUrl = pokemon.artUrl(),
                        placeholderResource = R.drawable.pokeball_icon,
                        errorResource = R.drawable.pokeball_icon,
                        contentDescription = pokemonDetail.name,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                        .verticalScroll(state = rememberScrollState())
                ) {
                    PokemonTabLayout(
                        onClick = onClickedTab,
                        tabs = tabs,
                        tabIndex = tabIndex,
                        pokemonDetailUi = pokemonDetail
                    )
//                    PokemonInfoStats(stats = pokemon.stats)
//                    StatBlock(stats = pokemon.stats)

                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewPokemonInfo() {
    PokemonInfo(
        pokemonDetail = PokemonDetailUi(id = 10249, name = "Enamorus-therian"),
        innerPadding = PaddingValues(),
        tabs = listOf("About", "Base Stats"),
        tabIndex = 1,
        onClickedTab = {}
    )

}