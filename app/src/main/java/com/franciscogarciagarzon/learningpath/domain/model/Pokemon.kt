package com.franciscogarciagarzon.learningpath.domain.model

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDto

data class Pokemon(
    val defaultSprite: String,
    val name: String,
    val url: URL
)

fun pokemonUrlToSpriteUrl(url: String): String {
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    val segments = url.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    val id = segments.last()

    return "$spriteUrl$id.png"

}

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        defaultSprite = pokemonUrlToSpriteUrl(this.url),
        name = this.name,
        url = this.url
    )

}