package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.franciscogarciagarzon.learningpath.R
import com.franciscogarciagarzon.learningpath.data.mock.MockDataSource
import com.franciscogarciagarzon.learningpath.ui.model.PokemonUi
import com.franciscogarciagarzon.learningpath.ui.model.fallbackSpriteUrl
import com.franciscogarciagarzon.learningpath.ui.model.toPokemonListUi
import com.franciscogarciagarzon.learningpath.ui.screens.components.RemoteImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonListCard(pokemon: PokemonUi, clickAction: () -> Unit) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .size(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .background(
                MaterialTheme.colorScheme.secondaryContainer
            )
            .clickable { clickAction() },

        ) {
        Column {
            RemoteImage(
                imageUrl = pokemon.defaultSprite,
                fallbackUrl = pokemon.fallbackSpriteUrl(),
                contentDescription = pokemon.name,
                errorResource = R.drawable.ic_error,
                placeholderResource = R.drawable.ic_pokeball_icon,
                modifier = Modifier
                    .size(135.dp)
                    .align(CenterHorizontally)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .basicMarquee()
                    .padding(start = 10.dp, end = 10.dp),
                text = pokemon.name.capitalize(locale = Locale.current),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

}

@Preview
@Composable
fun PokemonListCardPreview() {
    PokemonListCard(
        pokemon = MockDataSource().getPokemonListDto().toPokemonListUi().pokemons.first(),
        clickAction = {}
    )
}

fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
    val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    Palette.from(bmp).generate { palette ->
        palette?.dominantSwatch?.rgb?.let { colorValue ->
            onFinish(Color(colorValue))
        }
    }
}