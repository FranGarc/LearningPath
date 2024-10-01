package com.franciscogarciagarzon.learningpath.data.remote.model


import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexItem
import com.franciscogarciagarzon.learningpath.domain.model.URL
import com.google.gson.annotations.SerializedName

data class PokedexIndexItemDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)



fun PokedexIndexItemDto.toPokedexIndexItem(): PokedexIndexItem {
    return PokedexIndexItem(
        id = this.url.extractId(),
        name = this.name,
        url = this.url
    )

}
fun URL.extractId(): Int{
    val segments = this.split("/").toMutableList()
    segments.removeAll(listOf("", null))
    return segments.last().toInt()
}