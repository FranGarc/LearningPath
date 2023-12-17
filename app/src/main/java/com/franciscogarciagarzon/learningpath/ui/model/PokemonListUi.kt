package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto


data class PokemonListUi(
    val pokemons: List<PokemonUi>
)

fun PokemonListDto.toPokemonListUi() = PokemonListUi(
    pokemons = this.pokemons.map { pokemonDto -> pokemonDto.toPokemonUi() }
)