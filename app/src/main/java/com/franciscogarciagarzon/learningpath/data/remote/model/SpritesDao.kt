package com.franciscogarciagarzon.learningpath.data.remote.model


import android.util.Log
import com.franciscogarciagarzon.learningpath.domain.model.DreamWorldDto
import com.franciscogarciagarzon.learningpath.domain.model.HomeDto
import com.franciscogarciagarzon.learningpath.domain.model.OfficialArtworkDto
import com.franciscogarciagarzon.learningpath.domain.model.OtherDto
import com.franciscogarciagarzon.learningpath.domain.model.SpritesDto
import com.google.gson.annotations.SerializedName

data class SpritesDao(
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
    val other: OtherDao,
    @SerializedName("versions")
    val versions: Versions
)

fun SpritesDao.toSpritesDto(): SpritesDto {
    Log.d("SpritesDao", "toSpritesDto() dreamWorld: ${this.other.dreamWorld}")
    return SpritesDto(
        backDefault = this.backDefault ?: "",
        backFemale = this.backFemale ?: "",
        backShiny = this.backShiny ?: "",
        backShinyFemale = this.backShinyFemale ?: "",
        frontDefault = this.frontDefault ?: "",
        frontFemale = this.frontFemale ?: "",
        frontShiny = this.frontShiny ?: "",
        frontShinyFemale = this.frontShinyFemale ?: "",
        other = OtherDto(
            dreamWorld = DreamWorldDto(
                frontDefault = this.other.dreamWorld.frontDefault ?: "",
                frontFemale = this.other.dreamWorld.frontFemale ?: "",
            ),
            home = HomeDto(
                frontDefault = this.other.home.frontDefault ?: "",
                frontFemale = this.other.home.frontFemale ?: "",
                frontShiny = this.other.home.frontShiny ?: "",
                frontShinyFemale = this.other.home.frontShinyFemale ?: "",
            ),
            officialArtwork = OfficialArtworkDto(
                frontShiny = this.other.officialArtwork.frontShiny ?: "",
                frontDefault = this.other.officialArtwork.frontDefault ?: "",
            )
        ),
    )
}