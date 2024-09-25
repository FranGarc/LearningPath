package com.franciscogarciagarzon.learningpath.domain.model

data class PokedexIndexItem(
    val name: String,
    val url: URL
)

fun PokedexIndexItem.extractId(): String{
    val segments = this.url.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    return segments.last()
}

