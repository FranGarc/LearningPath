package com.franciscogarciagarzon.learningpath.data.mock

import com.franciscogarciagarzon.learningpath.domain.model.Ability
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetail
import com.franciscogarciagarzon.learningpath.domain.model.Sprites
import com.franciscogarciagarzon.learningpath.domain.model.Stat
import com.franciscogarciagarzon.learningpath.domain.model.Stats
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.Pokemon
import com.franciscogarciagarzon.learningpath.domain.model.PokemonList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockDataSource : DatasourceAdapter {

    private val pokemonList = PokemonList(
        pokemons = listOf(
            Pokemon(name = "bulbasaur", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", url = "https://pokeapi.co/api/v2/pokemon/1/"),
            Pokemon(name = "ivysaur", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png", url = "https://pokeapi.co/api/v2/pokemon/2/"),
            Pokemon(name = "venusaur", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png", url = "https://pokeapi.co/api/v2/pokemon/3/"),
            Pokemon(name = "charmander", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png", url = "https://pokeapi.co/api/v2/pokemon/4/"),
            Pokemon(name = "charmeleon", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png", url = "https://pokeapi.co/api/v2/pokemon/5/"),
            Pokemon(name = "charizard", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png", url = "https://pokeapi.co/api/v2/pokemon/6/"),
            Pokemon(name = "squirtle", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png", url = "https://pokeapi.co/api/v2/pokemon/7/"),
            Pokemon(name = "wartortle", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png", url = "https://pokeapi.co/api/v2/pokemon/8/"),
            Pokemon(name = "blastoise", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png", url = "https://pokeapi.co/api/v2/pokemon/9/"),
            Pokemon(name = "caterpie", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png", url = "https://pokeapi.co/api/v2/pokemon/10/"),
            Pokemon(name = "metapod", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png", url = "https://pokeapi.co/api/v2/pokemon/11/"),
            Pokemon(name = "butterfree", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png", url = "https://pokeapi.co/api/v2/pokemon/12/"),
            Pokemon(name = "weedle", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png", url = "https://pokeapi.co/api/v2/pokemon/13/"),
            Pokemon(name = "kakuna", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png", url = "https://pokeapi.co/api/v2/pokemon/14/"),
            Pokemon(name = "beedrill", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/15.png", url = "https://pokeapi.co/api/v2/pokemon/15/"),
            Pokemon(name = "pidgey", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png", url = "https://pokeapi.co/api/v2/pokemon/16/"),
            Pokemon(name = "pidgeotto", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/17.png", url = "https://pokeapi.co/api/v2/pokemon/17/"),
            Pokemon(name = "pidgeot", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png", url = "https://pokeapi.co/api/v2/pokemon/18/"),
            Pokemon(name = "rattata", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/19.png", url = "https://pokeapi.co/api/v2/pokemon/19/"),
            Pokemon(name = "raticate", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png", url = "https://pokeapi.co/api/v2/pokemon/20/"),

            )
    )


    private val pokemonDetail = PokemonDetail(

        name = "Ditto",
        baseExperience = 101,
        height = 3,
        weight = 40,
        stats = Stats(
            hp = Stat(name = "hp", 75),
            attack = Stat(name = "attack", value = 34),
            defense = Stat(name = "defense", value = 42),
            specialAttack = Stat(name = "Sp. Attack", 26),
            specialDefense = Stat(name = "Sp. Defense", value = 45),
            speed = Stat("speed", value = 37)
        ),
        sprites = Sprites(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
            backDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            frontFemale = "",
            backFemale = "",
            frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/132.png",
            backShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/132.png",
            frontShinyFemale = "",
            backShinyFemale = "",
        ),
        types = listOf("normal")
    )

    fun getPokemonDetail(): PokemonDetail = PokemonDetail(
        baseExperience = pokemonDetail.baseExperience,
        height = pokemonDetail.height,
        name = pokemonDetail.name,
        sprites = Sprites(
            frontDefault = pokemonDetail.sprites.frontDefault!!,
        ),
        stats = Stats(
            hp = pokemonDetail.stats.hp,
            attack = pokemonDetail.stats.attack,
            defense = pokemonDetail.stats.defense,
            speed = pokemonDetail.stats.speed,
            specialAttack = pokemonDetail.stats.specialAttack,
            specialDefense = pokemonDetail.stats.specialDefense,
        ),
        weight = pokemonDetail.weight,
        types = pokemonDetail.types,
        id = 30,
        abilities = listOf(
            Ability(name = "compound-eyes", abilityUrl = "https://pokeapi.co/api/v2/ability/132/"),
            Ability(name = "friend-guard", abilityUrl = "https://pokeapi.co/api/v2/ability/132/")
        )
    )
    override suspend fun getPokemonDetail(id: String): Flow<Result<PokemonDetail>> = flow {
        delay(1000)
        emit(Result.Success(PokemonDetail()))
    }

    fun getMockPokemonList(): PokemonList = PokemonList(pokemonList.pokemons.map { pokemon ->
        Pokemon(
            name = pokemon.name, defaultSprite = pokemon.defaultSprite, url = pokemon.url
        )
    })

    override suspend fun getPokemonList(): Flow<Result<PokemonList>> = flow {
        delay(1000)
        emit(Result.Success(PokemonList(emptyList())))
    }


}