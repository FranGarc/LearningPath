package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.PokemonDto
import com.franciscogarciagarzon.learningpath.domain.model.URL

data class PokemonUi(
    val defaultSprite: String,
    val name: String,
    val url: URL
)

fun pokemonUrlToSpriteUrl(pokemonUrl: URL): URL {
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    val segments = pokemonUrl.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    val id = segments.last()

    return "$spriteUrl$id.png"

}

fun PokemonDto.toPokemonUi() = PokemonUi(
    defaultSprite = this.defaultSprite,
    name = this.name,
    url = this.url
)