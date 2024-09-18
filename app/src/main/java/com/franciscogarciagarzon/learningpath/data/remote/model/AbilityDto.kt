package com.franciscogarciagarzon.learningpath.data.remote.model


import com.franciscogarciagarzon.learningpath.domain.model.Ability
import com.google.gson.annotations.SerializedName

data class AbilityDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun AbilityDto.toAbilityDto(): Ability {
    return Ability(
        name = this.name,
        abilityUrl = this.url
    )
}