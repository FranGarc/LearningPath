package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

//import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi.toPokemonDetailUi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonInfo(
    pokemonDetail: PokemonDetailUi,
    innerPadding: PaddingValues,
    onClickedTab: (Int) -> Unit,
    tabs: List<String>,
    tabIndex: Int,
) {
    val pokemon: PokemonDetailUi = pokemonDetail
    val typeColorsGradient = Brush.verticalGradient(
        colors = pokemon.typeColors(),
        startY = 500f, endY = 800f
    )



    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(typeColorsGradient)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Spacer(modifier = Modifier.padding(top = 60.dp))



                Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    BoldLabel20(
                        text = pokemon.name,
                        textAlignment = TextAlign.Start,
                        modifier = Modifier
                            .weight(1f)
                            .basicMarquee()
                    )
                    BoldLabel20(
                        text = pokemon.printableId(),
                        textAlignment = TextAlign.End,
                        modifier = Modifier.weight(0.5f)
                    )
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
                    Box {
                        RemoteImage(
//                        imageUrl = pokemonDetail.sprites.frontDefault,
                            imageUrl = pokemon.artUrl(),
                            placeholderResource = R.drawable.ic_pokeball_icon,
                            errorResource = R.drawable.ic_error,
                            contentDescription = pokemonDetail.name,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
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
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewPokemonInfo() {
    PokemonInfo(
        pokemonDetail = PokemonDetailUi(id = 10249, name = "ogerpon-cornerstone-mask-que.te.cagas"),
        innerPadding = PaddingValues(),
        tabs = listOf("About", "Base Stats"),
        tabIndex = 1,
        onClickedTab = {}
    )

}