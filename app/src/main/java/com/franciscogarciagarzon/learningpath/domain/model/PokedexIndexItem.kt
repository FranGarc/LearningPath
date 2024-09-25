package com.franciscogarciagarzon.learningpath.domain.model

data class PokedexIndexItem(
    val defaultSprite: String,
    val name: String,
    val url: URL
)

fun calculateSpriteUrl(url: String): String {
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    val segments = url.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    val id = segments.last()

    return "$spriteUrl$id.png"

}