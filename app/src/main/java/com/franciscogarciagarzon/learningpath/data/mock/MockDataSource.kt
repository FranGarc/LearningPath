package com.franciscogarciagarzon.learningpath.data.mock

import com.franciscogarciagarzon.learningpath.domain.model.Ability
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.Sprites
import com.franciscogarciagarzon.learningpath.domain.model.Stat
import com.franciscogarciagarzon.learningpath.domain.model.Stats
import com.franciscogarciagarzon.learningpath.domain.RepositoryAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexItem
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockDataSource : RepositoryAdapter {

    private val pokedexIndexItemList = PokedexIndexList(
        pokedexIndexItems = listOf(
            PokedexIndexItem(id=1, name = "bulbasaur" , url = "https://pokeapi.co/api/v2/pokemon/1/"),
            PokedexIndexItem(id=2, name = "ivysaur",  url = "https://pokeapi.co/api/v2/pokemon/2/"),
            PokedexIndexItem(id=3, name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/"),
            PokedexIndexItem(id=4, name = "charmander", url = "https://pokeapi.co/api/v2/pokemon/4/"),
            PokedexIndexItem(id=5, name = "charmeleon", url = "https://pokeapi.co/api/v2/pokemon/5/"),
            PokedexIndexItem(id=6, name = "charizard", url = "https://pokeapi.co/api/v2/pokemon/6/"),
            PokedexIndexItem(id=7, name = "squirtle",  url = "https://pokeapi.co/api/v2/pokemon/7/"),
            PokedexIndexItem(id=8, name = "wartortle", url = "https://pokeapi.co/api/v2/pokemon/8/"),
            PokedexIndexItem(id=9, name = "blastoise", url = "https://pokeapi.co/api/v2/pokemon/9/"),
            PokedexIndexItem(id=10, name = "caterpie", url = "https://pokeapi.co/api/v2/pokemon/10/"),
            PokedexIndexItem(id=11, name = "metapod",  url = "https://pokeapi.co/api/v2/pokemon/11/"),
            PokedexIndexItem(id=12, name = "butterfree", url = "https://pokeapi.co/api/v2/pokemon/12/"),
            PokedexIndexItem(id=13, name = "weedle", url = "https://pokeapi.co/api/v2/pokemon/13/"),
            PokedexIndexItem(id=14, name = "kakuna",  url = "https://pokeapi.co/api/v2/pokemon/14/"),
            PokedexIndexItem(id=15, name = "beedrill", url = "https://pokeapi.co/api/v2/pokemon/15/"),
            PokedexIndexItem(id=16, name = "pidgey",  url = "https://pokeapi.co/api/v2/pokemon/16/"),
            PokedexIndexItem(id=17, name = "pidgeotto", url = "https://pokeapi.co/api/v2/pokemon/17/"),
            PokedexIndexItem(id=18, name = "pidgeot", url = "https://pokeapi.co/api/v2/pokemon/18/"),
            PokedexIndexItem(id=19, name = "rattata",  url = "https://pokeapi.co/api/v2/pokemon/19/"),
            PokedexIndexItem(id=20, name = "raticate", url = "https://pokeapi.co/api/v2/pokemon/20/"),
            )
    )


    private val pokemonArticle = PokemonArticle(
        id = 132,
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
           id = pokemon.id, name = pokemon.name, url = pokemon.url
        )
    })

    override suspend fun getPokedexIndexList(): Flow<Result<PokedexIndexList>> = flow {
        delay(1000)
        emit(Result.Success(PokedexIndexList(emptyList())))
    }


}