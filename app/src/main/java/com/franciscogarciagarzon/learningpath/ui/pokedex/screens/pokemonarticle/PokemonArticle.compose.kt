package com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokemonarticle

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
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.toStatsUi
import com.franciscogarciagarzon.learningpath.ui.common.components.BoldLabel
import com.franciscogarciagarzon.learningpath.ui.common.components.RegularLabel
import com.franciscogarciagarzon.learningpath.ui.common.components.RemoteImage

@Composable
fun PokemonArticle(
    pokemonArticle: PokemonArticle,
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
                imageUrl = pokemonArticle.sprites.frontDefault,
                fallbackUrl = "",
                placeholderResource = R.drawable.ic_pokeball_icon,
                errorResource = R.drawable.ic_error,
                contentDescription = pokemonArticle.name,
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
                val types = pokemonArticle.types
                BoldLabel(text = "Type(s): ")
                RegularLabel(text = pokemonArticle.types.firstOrNull() ?: "")
                if (types.size > 1) {
                    BoldLabel(text = " / ")
                    RegularLabel(text = pokemonArticle.types.last())
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
                    RegularLabel(text = "${pokemonArticle.weight / 10}Kg")
                }

                Row(
                    modifier = Modifier
                        .weight(0.5f),
                    horizontalArrangement = Arrangement.End
                ) {
                    BoldLabel(text = "Height")
                    RegularLabel(text = "${pokemonArticle.height * 10}cm")
                }
            }
            // stats
            val stats = pokemonArticle.stats
            StatBlock(stats = stats.toStatsUi())

            Row(modifier = Modifier.height(30.dp)) {

            }
        }
    }
}

@Preview
@Composable
fun ArticlePreview() {
    val pokemonArticle = PokemonArticle(weight = 10000000, height = 10000)
    val innerPadding = PaddingValues()
    PokemonArticle(pokemonArticle, innerPadding)

}