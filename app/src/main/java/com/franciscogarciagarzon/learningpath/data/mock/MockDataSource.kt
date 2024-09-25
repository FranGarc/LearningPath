package com.franciscogarciagarzon.learningpath.data.mock

import com.franciscogarciagarzon.learningpath.domain.model.Ability
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.Sprites
import com.franciscogarciagarzon.learningpath.domain.model.Stat
import com.franciscogarciagarzon.learningpath.domain.model.Stats
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexItem
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockDataSource : DatasourceAdapter {

    private val pokedexIndexItemList = PokedexIndexList(
        pokedexIndexItems = listOf(
            PokedexIndexItem(name = "bulbasaur" , url = "https://pokeapi.co/api/v2/pokemon/1/"),
            PokedexIndexItem(name = "ivysaur",  url = "https://pokeapi.co/api/v2/pokemon/2/"),
            PokedexIndexItem(name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/"),
            PokedexIndexItem(name = "charmander", url = "https://pokeapi.co/api/v2/pokemon/4/"),
            PokedexIndexItem(name = "charmeleon", url = "https://pokeapi.co/api/v2/pokemon/5/"),
            PokedexIndexItem(name = "charizard", url = "https://pokeapi.co/api/v2/pokemon/6/"),
            PokedexIndexItem(name = "squirtle",  url = "https://pokeapi.co/api/v2/pokemon/7/"),
            PokedexIndexItem(name = "wartortle", url = "https://pokeapi.co/api/v2/pokemon/8/"),
            PokedexIndexItem(name = "blastoise", url = "https://pokeapi.co/api/v2/pokemon/9/"),
            PokedexIndexItem(name = "caterpie", url = "https://pokeapi.co/api/v2/pokemon/10/"),
            PokedexIndexItem(name = "metapod",  url = "https://pokeapi.co/api/v2/pokemon/11/"),
            PokedexIndexItem(name = "butterfree", url = "https://pokeapi.co/api/v2/pokemon/12/"),
            PokedexIndexItem(name = "weedle", url = "https://pokeapi.co/api/v2/pokemon/13/"),
            PokedexIndexItem(name = "kakuna",  url = "https://pokeapi.co/api/v2/pokemon/14/"),
            PokedexIndexItem(name = "beedrill", url = "https://pokeapi.co/api/v2/pokemon/15/"),
            PokedexIndexItem(name = "pidgey",  url = "https://pokeapi.co/api/v2/pokemon/16/"),
            PokedexIndexItem(name = "pidgeotto", url = "https://pokeapi.co/api/v2/pokemon/17/"),
            PokedexIndexItem(name = "pidgeot", url = "https://pokeapi.co/api/v2/pokemon/18/"),
            PokedexIndexItem(name = "rattata",  url = "https://pokeapi.co/api/v2/pokemon/19/"),
            PokedexIndexItem(name = "raticate", url = "https://pokeapi.co/api/v2/pokemon/20/"),
            )
    )


    private val pokemonArticle = PokemonArticle(

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

    fun getPokemonArticle(): PokemonArticle = PokemonArticle(
        baseExperience = pokemonArticle.baseExperience,
        height = pokemonArticle.height,
        name = pokemonArticle.name,
        sprites = Sprites(
            frontDefault = pokemonArticle.sprites.frontDefault!!,
        ),
        stats = Stats(
            hp = pokemonArticle.stats.hp,
            attack = pokemonArticle.stats.attack,
            defense = pokemonArticle.stats.defense,
            speed = pokemonArticle.stats.speed,
            specialAttack = pokemonArticle.stats.specialAttack,
            specialDefense = pokemonArticle.stats.specialDefense,
        ),
        weight = pokemonArticle.weight,
        types = pokemonArticle.types,
        id = 30,
        abilities = listOf(
            Ability(name = "compound-eyes", abilityUrl = "https://pokeapi.co/api/v2/ability/132/"),
            Ability(name = "friend-guard", abilityUrl = "https://pokeapi.co/api/v2/ability/132/")
        )
    )
    override suspend fun getPokemonArticle(id: String): Flow<Result<PokemonArticle>> = flow {
        delay(1000)
        emit(Result.Success(PokemonArticle()))
    }

    fun getMockPokedexIndexList(): PokedexIndexList = PokedexIndexList(pokedexIndexItemList.pokedexIndexItems.map { pokemon ->
        PokedexIndexItem(
            name = pokemon.name, url = pokemon.url
        )
    })

    override suspend fun getPokedexIndexList(): Flow<Result<PokedexIndexList>> = flow {
        delay(1000)
        emit(Result.Success(PokedexIndexList(emptyList())))
    }


}