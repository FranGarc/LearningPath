package com.franciscogarciagarzon.learningpath.domain.model

data class OtherDto(
    val dreamWorld: DreamWorldDto = DreamWorldDto(),
    val home: HomeDto = HomeDto(),
    val officialArtwork: OfficialArtworkDto = OfficialArtworkDto(),
)
