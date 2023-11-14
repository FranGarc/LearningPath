package com.franciscogarciagarzon.learningpath.domain.model

data class PokemonDto(
    val defaultSprite: String,
    val name: String,
    val url: String
)

fun pokemonUrlToSpriteUrl(url: String): String {
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    val segments = url.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    val id = segments.last()

    return "$spriteUrl$id.png"

}

fun com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDao.toPokemonDto(): PokemonDto {
    return PokemonDto(
        defaultSprite = pokemonUrlToSpriteUrl(this.url),
        name = this.name,
        url = this.url
    )

}