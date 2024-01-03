package com.franciscogarciagarzon.learningpath.data.remote.model


import com.franciscogarciagarzon.learningpath.domain.model.AbilityDto
import com.google.gson.annotations.SerializedName

data class AbilityDao(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun AbilityDao.toAbilityDto(): AbilityDto {
    return AbilityDto(
        name = this.name,
        abilityUrl = this.url
    )
}