package com.franciscogarciagarzon.learningpath.ui.model

import androidx.compose.ui.graphics.Color
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.URL
import com.franciscogarciagarzon.learningpath.ui.extensions.capitalizeLP
import com.franciscogarciagarzon.learningpath.ui.extensions.darkenBy
import com.franciscogarciagarzon.learningpath.ui.extensions.lightenBy


data class PokemonDetailUi(
    val id: Int = -1,
    val name: String = "pokemon",
    val height: Int = -1,  // (x*10) cm
    val weight: Int = -1, // (x/10) kg
    val sprites: SpritesUi = SpritesUi(male = SpriteSet(front = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png")),
    val types: List<TypeUi> = listOf(TypeUi.Normal),
    val stats: StatsUi = StatsUi(),
    val baseExperience: Int = -1,

    ) {
    fun isLoaded(): Boolean = (id != -1)

    fun printableId(): String = "#" + id.toString().padStart(length = 3, padChar = '0')
    fun printableWeight(): String = "${weight}Kg"
    fun printableHeight(): String = "${height}Cm"
    fun typeColors(): List<Color> = if (types.size > 1) {
        listOf(types.first().color.darkenBy(.4f), types.last().color.lightenBy(.25f))
    } else {
        listOf(types.first().color.darkenBy(.4f), types.first().color.lightenBy(.1f))
    }

    fun artUrl(): URL = sprites.frontSpriteUrl()
//        sprites.male.front.ifEmpty {
//        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
//    }
}

fun PokemonDetailDto.toPokemonDetailUi() = PokemonDetailUi(
    id = this.id,
    name = this.name.capitalizeLP(),
    height = this.height * 10,
    weight = this.weight / 10,
    sprites = this.sprites.toSpritesUi(),
    types = this.types.map { type -> type.toTypeUi() },
    stats = this.stats.toStatsUi()
)

