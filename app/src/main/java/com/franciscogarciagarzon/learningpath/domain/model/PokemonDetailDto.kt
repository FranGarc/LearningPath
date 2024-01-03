package com.franciscogarciagarzon.learningpath.domain.model

data class PokemonDetailDto(
    val baseExperience: Int = -1,
    val height: Int = -1,
    val name: String = "pokemonDto",
    val sprites: SpritesDto = SpritesDto(),
    val stats: StatsDto = StatsDto(),
    val weight: Int = -1,
    val types: List<String> = listOf("normal", "ghost"),
    val id: Int = -1,
    val abilities: List<AbilityDto> = listOf()
) {
    fun isLoaded(): Boolean = (id != -1)
}





