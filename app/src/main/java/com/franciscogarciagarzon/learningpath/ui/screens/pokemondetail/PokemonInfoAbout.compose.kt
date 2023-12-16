package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonDetailUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel

@Composable
fun PokemonInfoAbout(pokemonDetailUi: PokemonDetailUi) {
//    Text("About This Pokemon")
    val paddingContainerTop = 10.dp
    val paddingRowTop = 15.dp
    val paddingBetweenColumns = 45.dp
    val paddingStart = 10.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingContainerTop)
    ) {
        Column(modifier = Modifier.padding(start = paddingStart)) {
            Row(
                modifier = Modifier
                    .padding(top = paddingRowTop)
            ) {
                RegularLabel(text = "Height")
            }
            Row(
                modifier = Modifier
                    .padding(top = paddingRowTop)
            ) {
                RegularLabel(text = "Weight")
            }
            Row(
                modifier = Modifier
                    .padding(top = paddingRowTop)
            ) {
                RegularLabel(text = "Ability")
            }
        }
        Column(modifier = Modifier.padding(start = paddingBetweenColumns)) {
            Row(
                modifier = Modifier
                    .padding(top = paddingRowTop)
            ) {
                RegularLabel(text = pokemonDetailUi.printableHeight())
            }
            Row(
                modifier = Modifier
                    .padding(top = paddingRowTop)
            ) {
                RegularLabel(text = pokemonDetailUi.printableWeight())
            }
            Row(
                modifier = Modifier
                    .padding(top = paddingRowTop)
            ) {
//                RegularLabel(text = pokemonDetailUi)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PokemonInfoAboutPreview() {
    PokemonInfoAbout(
        pokemonDetailUi = MockDataSource().getPokemonDetailDto().toPokemonDetailUi()
    )
}