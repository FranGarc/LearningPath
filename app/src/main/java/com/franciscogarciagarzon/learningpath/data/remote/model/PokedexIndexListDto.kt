package com.franciscogarciagarzon.learningpath.data.remote.model


import com.franciscogarciagarzon.learningpath.domain.model.DataModelNotCompatibleWithDomainModelException
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.google.gson.annotations.SerializedName

data class PokedexIndexListDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PokedexIndexItemDto>
)

fun PokedexIndexListDto.toPokedexIndexList(): PokedexIndexList {
    try {
        return PokedexIndexList(pokedexIndexItems = this.results.map { pokemonDto -> pokemonDto.toPokedexIndexItem() })
    } catch (e: Exception) {
        e.printStackTrace()
        throw DataModelNotCompatibleWithDomainModelException(e.message)
    }

}