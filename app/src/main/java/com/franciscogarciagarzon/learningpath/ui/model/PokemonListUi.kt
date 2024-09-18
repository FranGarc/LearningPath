package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.PokemonList


data class PokemonListUi(
    val pokemons: List<PokemonUi>
)

fun PokemonList.toPokemonListUi() = PokemonListUi(
    pokemons = this.pokemons.map { pokemonDto -> pokemonDto.toPokemonUi() }
)