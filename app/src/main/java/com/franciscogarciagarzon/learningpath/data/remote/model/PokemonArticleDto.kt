package com.franciscogarciagarzon.learningpath.data.remote.model


import android.util.Log
import com.franciscogarciagarzon.learningpath.domain.model.DataModelNotCompatibleWithDomainModelException
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.Stat
import com.franciscogarciagarzon.learningpath.domain.model.Stats
import com.google.gson.annotations.SerializedName

data class PokemonArticleDto(
    @SerializedName("abilities")
    val abilities: List<AbilityWrapperDto>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("cries")
    val cries: Cries,
    @SerializedName("forms")
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any?>,
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
    @SerializedName("past_abilities")
    val pastAbilities: List<Any?>,
    @SerializedName("past_types")
    val pastTypes: List<Any?>,
    @SerializedName("species")
    val species: Species,
    @SerializedName("sprites")
    val sprites: SpritesDto,
    @SerializedName("stats")
    val stats: List<StatWrapperDto>,
    @SerializedName("types")
    val types: List<TypeDto>,
    @SerializedName("weight")
    val weight: Int
)

fun PokemonArticleDto.toPokemonArticle(): PokemonArticle {
    try {
        Log.d("PokemonDetailDto", "sprites: " + this.sprites)
        return PokemonArticle(
            baseExperience = this.baseExperience,
            height = this.height,
            name = this.name,
            weight = this.weight,
            types = this.types.toDomainTypes(),
            sprites = this.sprites.toSprites(),
            stats = this.stats.toStats(),
            id = this.id,
            abilities = this.abilities.map { wrapper -> wrapper.ability.toAbilityDto() }
        )
    } catch (e: Exception) {
        e.printStackTrace()
        throw DataModelNotCompatibleWithDomainModelException(e.message)
    }
}


fun List<StatWrapperDto>.toStats(): Stats {
    var attack: Stat = Stat()
    var defense: Stat = Stat()
    var hp: Stat = Stat()
    var specialAttack: Stat = Stat()
    var specialDefense: Stat = Stat()
    var speed: Stat = Stat()

    for (stat in this) {
        when (stat.stat.name) {
            StatNames.ATTACK -> attack = Stat(name = stat.stat.name, value = stat.baseStat)
            StatNames.DEFENSE -> defense = Stat(name = stat.stat.name, value = stat.baseStat)
            StatNames.HP -> hp = Stat(name = stat.stat.name, value = stat.baseStat)
            StatNames.SP_ATTACK -> specialAttack = Stat(name = "Sp. Attack", value = stat.baseStat)
            StatNames.SP_DEFENSE -> specialDefense = Stat(name = "Sp. Defense", value = stat.baseStat)
            StatNames.SPEED -> speed = Stat(name = stat.stat.name, value = stat.baseStat)
        }
    }

    return Stats(
        attack = attack,
        defense = defense,
        hp = hp,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed
    )

}

object StatNames{
    const val ATTACK = "attack"
    const val DEFENSE = "defense"
    const val HP = "hp"
    const val SP_ATTACK = "special-attack"
    const val SP_DEFENSE = "special-defense"
    const val SPEED = "speed"

}