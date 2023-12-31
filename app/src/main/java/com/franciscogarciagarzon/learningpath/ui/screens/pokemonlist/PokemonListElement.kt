package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franciscogarciagarzon.learningpath.R
import com.franciscogarciagarzon.learningpath.data.MockDataSource
import com.franciscogarciagarzon.learningpath.data.model.Pokemon
import com.franciscogarciagarzon.learningpath.ui.screens.components.RemoteImage

@Composable
fun PokemonListElement(pokemon: Pokemon, clickAction: () -> Unit) {
    Row(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clickable { clickAction() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RemoteImage(
            imageUrl = pokemon.defaultSprite,
            contentDescription = pokemon.name,
            errorResource = R.drawable.pokeball_icon,
            placeholderResource = R.drawable.pokeball_icon
        )

        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            text = pokemon.name.capitalize(locale = Locale.current),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ListElementPreview() {
    PokemonListElement(
        pokemon = MockDataSource().getPokemonList().pokemons.first(),
        clickAction = {}
    )
}