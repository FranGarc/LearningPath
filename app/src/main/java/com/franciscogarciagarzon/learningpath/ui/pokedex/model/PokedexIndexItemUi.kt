package com.franciscogarciagarzon.learningpath.ui.pokedex.model

import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexItem
import com.franciscogarciagarzon.learningpath.domain.model.URL
import com.franciscogarciagarzon.learningpath.domain.model.extractId

data class PokedexIndexItemUi(
    val defaultSprite: String,
    val name: String,
    val url: URL
)

fun PokedexIndexItemUi.fallbackSpriteUrl(): URL {
    val segments = this.url.split("/").toMutableList()
    val id = segments.dropLast(1).last()
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    return "$spriteUrl$id.png"
}


fun PokedexIndexItem.toPokedexIndexItemUi() = PokedexIndexItemUi(
    defaultSprite = calculateSpriteUrlFromId(this.extractId()),
    name = this.name,
    url = this.url
)

fun calculateSpriteUrlFromId(id: String): URL {
    val spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/"
    return "$spriteUrl$id.svg"
}