package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.PokemonDto
import com.franciscogarciagarzon.learningpath.domain.model.URL

data class PokemonUi(
    val defaultSprite: String,
    val name: String,
    val url: URL
)

fun PokemonUi.fallbackSpriteUrl(): String {
    val segments = this.url.split("/").toMutableList()
    val id = segments.dropLast(1).last()
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    return "$spriteUrl$id.png"
}

fun pokemonUrlToSpriteUrl(pokemonUrl: URL): URL {
    val segments = pokemonUrl.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    val id = segments.last()
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
//    return "$spriteUrl$id.png"
    return spriteUrl

}

fun PokemonDto.toPokemonUi() = PokemonUi(
    defaultSprite = pokemonUrlToSpriteUrl(this.url),
    name = this.name,
    url = this.url
)