package com.franciscogarciagarzon.learningpath.data.remote.model


import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexItem
import com.franciscogarciagarzon.learningpath.domain.model.calculateSpriteUrl
import com.google.gson.annotations.SerializedName

data class PokedexIndexItemDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)



fun PokedexIndexItemDto.toPokedexIndexItem(): PokedexIndexItem {
    return PokedexIndexItem(
        defaultSprite = calculateSpriteUrl(this.url),
        name = this.name,
        url = this.url
    )

}