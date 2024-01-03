package com.franciscogarciagarzon.learningpath.data.remote.model


import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.StatDto
import com.franciscogarciagarzon.learningpath.domain.model.StatsDto
import com.google.gson.annotations.SerializedName

data class PokemonDetailDao(
    @SerializedName("abilities")
    val abilities: List<AbilityWrapperDao>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("forms")
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<HeldItem>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("moves")
    val moves: List<Move>,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    @SerializedName("species")
    val species: Species,
    @SerializedName("sprites")
    val sprites: SpritesDao,
    @SerializedName("stats")
    val stats: List<ExternalStatDao>,
    @SerializedName("types")
    val types: List<TypeDao>,
    @SerializedName("weight")
    val weight: Int
)

fun PokemonDetailDao.toPokemonDetailDto(): PokemonDetailDto {
    return PokemonDetailDto(
        baseExperience = this.baseExperience,
        height = this.height,
        name = this.name,
        weight = this.weight,
        types = this.types.toDomainTypes(),
        sprites = this.sprites.toSpritesDto(),
        stats = this.stats.toStatsDto(),
        id = this.id,
        abilities = this.abilities.map { wrapper -> wrapper.ability.toAbilityDto() }
    )
}


fun List<ExternalStatDao>.toStatsDto(): StatsDto {
    var attack: StatDto = StatDto()
    var defense: StatDto = StatDto()
    var hp: StatDto = StatDto()
    var specialAttack: StatDto = StatDto()
    var specialDefense: StatDto = StatDto()
    var speed: StatDto = StatDto()

    for (stat in this) {
        when (stat.stat.name) {
            "attack" -> attack = StatDto(name = stat.stat.name, value = stat.baseStat)
            "defense" -> defense = StatDto(name = stat.stat.name, value = stat.baseStat)
            "hp" -> hp = StatDto(name = stat.stat.name, value = stat.baseStat)
            "special-attack" -> specialAttack = StatDto(name = "Sp. Attack", value = stat.baseStat)
            "special-defense" -> specialDefense = StatDto(name = "Sp. Defense", value = stat.baseStat)
            "speed" -> speed = StatDto(name = stat.stat.name, value = stat.baseStat)
        }
    }

    return StatsDto(
        attack = attack,
        defense = defense,
        hp = hp,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed
    )

}