package com.franciscogarciagarzon.learningpath.ui.pokedex.model

import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList


data class PokemonListUi(
    val pokemons: List<PokemonUi>
)

fun PokedexIndexList.toPokemonListUi() = PokemonListUi(
    pokemons = this.pokedexIndexItems.map { pokemonDto -> pokemonDto.toPokemonUi() }
)