package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.SpritesDto
import com.franciscogarciagarzon.learningpath.domain.model.URL

data class SpritesUi(
    val male: SpriteSet = SpriteSet(),
    val female: SpriteSet = SpriteSet(),
    val shinyMale: SpriteSet = SpriteSet(),
    val shinyFemale: SpriteSet = SpriteSet(),
    val other: OtherUi = OtherUi()
) {
    fun frontSpriteUrl(): URL {
        return other.dreamWorld.frontDefault.let { dreamworldDefault ->
            if (dreamworldDefault.isNotEmpty()) {
                dreamworldDefault
            } else {
                other.officialArtwork.frontDefault.let { officialDefault ->
                    if (officialDefault.isNotEmpty()) {
                        officialDefault
                    } else {
                        other.home.frontDefault.let { homeDefault ->
                            if (homeDefault.isNotEmpty()) {
                                homeDefault
                            } else {
                                male.front
                            }
                        }
                    }
                }
            }

        }

    }
}


data class SpriteSet(
    val front: URL = "",
    val back: URL = "",
)

fun SpritesDto.toSpritesUi() = SpritesUi(
    male = SpriteSet(
        front = this.frontDefault,
        back = this.backDefault
    ),
    female = SpriteSet(
        front = this.frontFemale,
        back = this.backFemale,
    ),
    shinyMale = SpriteSet(
        front = this.frontShiny,
        back = this.backShiny,
    ),
    shinyFemale = SpriteSet(
        front = this.frontShinyFemale,
        back = this.backShinyFemale,
    ),
    other = OtherUi(
        dreamWorld = DreamWorldUi(
            frontDefault = this.other.dreamWorld.frontDefault,
            frontFemale = this.other.dreamWorld.frontFemale
        ),
        home = HomeUi(
            frontDefault = this.other.home.frontDefault,
            frontShiny = this.other.home.frontShiny,
            frontFemale = this.other.home.frontShiny,
            frontShinyFemale = this.other.home.frontShinyFemale
        ),
        officialArtwork = OfficialArtworkUi(
            frontShiny = this.other.officialArtwork.frontShiny,
            frontDefault = this.other.officialArtwork.frontDefault
        )
    )
)
