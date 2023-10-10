package com.franciscogarciagarzon.learningpath.data

import com.franciscogarciagarzon.learningpath.data.model.PokemonDao
import com.franciscogarciagarzon.learningpath.data.model.PokemonDetailDao
import com.franciscogarciagarzon.learningpath.data.model.PokemonListDao
import com.franciscogarciagarzon.learningpath.data.model.SpritesDao
import com.franciscogarciagarzon.learningpath.data.model.StatDao
import com.franciscogarciagarzon.learningpath.data.model.StatsDao
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.toPokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.toPokemonListDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockDataSource : DatasourceAdapter {

    private val pokemonList = PokemonListDao(
        pokemons = listOf(
            PokemonDao(name = "bulbasaur", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", url = "https://pokeapi.co/api/v2/pokemon/1/"),
            PokemonDao(name = "ivysaur", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png", url = "https://pokeapi.co/api/v2/pokemon/2/"),
            PokemonDao(name = "venusaur", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png", url = "https://pokeapi.co/api/v2/pokemon/3/"),
            PokemonDao(name = "charmander", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png", url = "https://pokeapi.co/api/v2/pokemon/4/"),
            PokemonDao(name = "charmeleon", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png", url = "https://pokeapi.co/api/v2/pokemon/5/"),
            PokemonDao(name = "charizard", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png", url = "https://pokeapi.co/api/v2/pokemon/6/"),
            PokemonDao(name = "squirtle", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png", url = "https://pokeapi.co/api/v2/pokemon/7/"),
            PokemonDao(name = "wartortle", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png", url = "https://pokeapi.co/api/v2/pokemon/8/"),
            PokemonDao(name = "blastoise", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png", url = "https://pokeapi.co/api/v2/pokemon/9/"),
            PokemonDao(name = "caterpie", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png", url = "https://pokeapi.co/api/v2/pokemon/10/"),
            PokemonDao(name = "metapod", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png", url = "https://pokeapi.co/api/v2/pokemon/11/"),
            PokemonDao(name = "butterfree", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png", url = "https://pokeapi.co/api/v2/pokemon/12/"),
            PokemonDao(name = "weedle", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png", url = "https://pokeapi.co/api/v2/pokemon/13/"),
            PokemonDao(name = "kakuna", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png", url = "https://pokeapi.co/api/v2/pokemon/14/"),
            PokemonDao(name = "beedrill", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/15.png", url = "https://pokeapi.co/api/v2/pokemon/15/"),
            PokemonDao(name = "pidgey", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png", url = "https://pokeapi.co/api/v2/pokemon/16/"),
            PokemonDao(name = "pidgeotto", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/17.png", url = "https://pokeapi.co/api/v2/pokemon/17/"),
            PokemonDao(name = "pidgeot", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png", url = "https://pokeapi.co/api/v2/pokemon/18/"),
            PokemonDao(name = "rattata", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/19.png", url = "https://pokeapi.co/api/v2/pokemon/19/"),
            PokemonDao(name = "raticate", defaultSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png", url = "https://pokeapi.co/api/v2/pokemon/20/"),

            )
    )


    private val pokemonDetail = PokemonDetailDao(
        name = "Ditto",
        baseExperience = 101,
        height = 3,
        weight = 40,
        stats = StatsDao(
            hp = StatDao(name = "hp", 75),
            attack = StatDao(name = "attack", value = 34),
            defense = StatDao(name = "defense", value = 42),
            specialAttack = StatDao(name = "Sp. Attack", 26),
            specialDefense = StatDao(name = "Sp. Defense", value = 45),
            speed = StatDao("speed", value = 37)
        ),
        sprites = SpritesDao(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
            backDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            frontFemale = null,
            backFemale = null,
            frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/132.png",
            backShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/132.png",
            frontShinyFemale = null,
            backShinyFemale = null,
        ),
        types = listOf("normal")
    )

    override suspend fun getPokemonList(): Flow<PokemonListDto> = flow {
        delay(1000)
        emit(pokemonList.toPokemonListDto())
    }

    override suspend fun getPokemonDetail(id: String): Flow<PokemonDetailDto> = flow {
        delay(1000)
        emit(pokemonDetail.toPokemonDetailDto())
    }
}