package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.SpritesDto

data class SpritesUi(
    val male: SpriteSet = SpriteSet(),
    val female: SpriteSet = SpriteSet(),
    val shinyMale: SpriteSet = SpriteSet(),
    val shinyFemale: SpriteSet = SpriteSet(),
)

data class SpriteSet(
    val front: String = "",
    val back: String = "",
)

fun SpritesDto.toSpritesUi() = SpritesUi(
    male = SpriteSet(
        front = this.frontDefault,
        back = this.backDefault
    ),
    female = SpriteSet(
        front = this.frontFemale.orEmpty(),
        back = this.backFemale.orEmpty()
    ),
    shinyMale = SpriteSet(
        front = this.frontShiny,
        back = this.backShiny
    ),
    shinyFemale = SpriteSet(
        front = this.frontShinyFemale.orEmpty(),
        back = this.backShinyFemale.orEmpty()
    )
)
