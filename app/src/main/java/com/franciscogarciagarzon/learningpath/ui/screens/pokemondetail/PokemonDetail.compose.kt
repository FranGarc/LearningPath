package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.R
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.ui.model.toStatsUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.BoldLabel
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel
import com.franciscogarciagarzon.learningpath.ui.screens.components.RemoteImage

@Composable
fun PokemonDetail(
    pokemonDetail: PokemonDetailDto,
    innerPadding: PaddingValues,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxHeight()
            .verticalScroll(state = scrollState)
            .background(MaterialTheme.colorScheme.background),

        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.secondaryContainer),
        ) {

            RemoteImage(
                imageUrl = pokemonDetail.sprites.frontDefault,
                placeholderResource = R.drawable.pokeball_icon,
                errorResource = R.drawable.pokeball_icon,
                contentDescription = pokemonDetail.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
//                    .scale(1f)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        ) {


            Row(//types
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val types = pokemonDetail.types
                BoldLabel(text = "Type(s): ")
                RegularLabel(text = pokemonDetail.types.firstOrNull() ?: "")
                if (types.size > 1) {
                    BoldLabel(text = " / ")
                    RegularLabel(text = pokemonDetail.types.last())
                }
            }
            Row(
// weight & height
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .fillMaxWidth(),
            ) {

                Row(
                    modifier = Modifier
                        .weight(0.5f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    BoldLabel(text = "Weight: ")
                    RegularLabel(text = "${pokemonDetail.weight / 10}Kg")
                }

                Row(
                    modifier = Modifier
                        .weight(0.5f),
                    horizontalArrangement = Arrangement.End
                ) {
                    BoldLabel(text = "Height")
                    RegularLabel(text = "${pokemonDetail.height * 10}cm")
                }
            }
            // stats
            val stats = pokemonDetail.stats
            StatBlock(stats = stats.toStatsUi())

            Row(modifier = Modifier.height(30.dp)) {

            }
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
    val pokemonDetail = PokemonDetailDto(weight = 10000000, height = 10000)
    val innerPadding = PaddingValues()
    PokemonDetail(pokemonDetail, innerPadding)

}