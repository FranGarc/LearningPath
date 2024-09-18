package com.franciscogarciagarzon.learningpath.data.remote.model


import android.util.Log
import com.franciscogarciagarzon.learningpath.domain.model.DreamWorld
import com.franciscogarciagarzon.learningpath.domain.model.Home
import com.franciscogarciagarzon.learningpath.domain.model.OfficialArtwork
import com.franciscogarciagarzon.learningpath.domain.model.Other
import com.franciscogarciagarzon.learningpath.domain.model.Sprites
import com.google.gson.annotations.SerializedName

data class SpritesDto(
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_female")
    val backFemale: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("back_shiny_female")
    val backShinyFemale: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?,
    @SerializedName("other")
    val other: OtherDto,
    @SerializedName("versions")
    val versions: Versions
)

fun SpritesDto.toSprites(): Sprites {
    Log.d("SpritesDto", "toSprites() dreamWorld: ${this.other.dreamWorld}")
    return Sprites(
        backDefault = this.backDefault ?: "",
        backFemale = this.backFemale ?: "",
        backShiny = this.backShiny ?: "",
        backShinyFemale = this.backShinyFemale ?: "",
        frontDefault = this.frontDefault ?: "",
        frontFemale = this.frontFemale ?: "",
        frontShiny = this.frontShiny ?: "",
        frontShinyFemale = this.frontShinyFemale ?: "",
        other = Other(
            dreamWorld = DreamWorld(
                frontDefault = this.other.dreamWorld.frontDefault ?: "",
                frontFemale = this.other.dreamWorld.frontFemale ?: "",
            ),
            home = Home(
                frontDefault = this.other.home.frontDefault ?: "",
                frontFemale = this.other.home.frontFemale ?: "",
                frontShiny = this.other.home.frontShiny ?: "",
                frontShinyFemale = this.other.home.frontShinyFemale ?: "",
            ),
            officialArtwork = OfficialArtwork(
                frontShiny = this.other.officialArtwork.frontShiny ?: "",
                frontDefault = this.other.officialArtwork.frontDefault ?: "",
            )
        ),
    )
}