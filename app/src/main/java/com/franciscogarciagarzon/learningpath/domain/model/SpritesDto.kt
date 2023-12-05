package com.franciscogarciagarzon.learningpath.domain.model

data class SpritesDto(
    val backDefault: URL = "",
    val backFemale: URL = "",
    val backShiny: URL = "",
    val backShinyFemale: URL = "",
    val frontDefault: URL = "",
    val frontFemale: URL = "",
    val frontShiny: URL = "",
    val frontShinyFemale: URL = "",
    val other: OtherDto = OtherDto(),

    // versions
)

// Other: OficialArtwork, Home, DreamWorld
// OficialArtwork: frontDefault (png, big), frontShiny (png, big)
// Home:  frontDefault (png big), frontFemale (null), frontShiny (png), frontShinyFemale (null)
// Dremaworld: frontDefault (svg mid-small), frontFemale (null)