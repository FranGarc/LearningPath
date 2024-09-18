package com.franciscogarciagarzon.learningpath.domain.model

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDto

data class PokemonList(
    val pokemons: List<Pokemon>
)


fun PokemonListDto.toPokemonList(): PokemonList {
    try {
        return PokemonList(pokemons = this.results.map { pokemonDto -> pokemonDto.toPokemon() })
    } catch (e: Exception) {
        e.printStackTrace()
        throw DataModelNotCompatibleWithDomainModelException(e.message)
    }

}



