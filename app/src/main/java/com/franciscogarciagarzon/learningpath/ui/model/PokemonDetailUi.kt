package com.franciscogarciagarzon.learningpath.ui.model

import androidx.compose.ui.graphics.Color
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.ui.extensions.capitalizeLP


data class PokemonDetailUi(
    val id: Int = -1,
    val name: String = "pokemon",
    val height: Int = -1,  // (x*10) cm
    val weight: Int = -1, // (x/10) kg
    val sprites: SpritesUi = SpritesUi(),
    val types: List<TypeUi> = emptyList(),
    val stats: StatsUi = StatsUi(),
    val baseExperience: Int = -1,

    ) {
    fun printableId(): String = "#" + id.toString().padStart(length = 3, padChar = '0')
    fun printableWeight(): String = "${weight}Kg"
    fun printableHeight(): String = "${height}Kg"
    fun getColors(): List<Color> = if (types.size > 1) {
        types.map { type -> type.color }.toList()
    } else {
        listOf(types.first().color, types.first().color)
    }

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

