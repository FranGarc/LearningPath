package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDetailDao
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDao
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface PokemonService {
    fun getPokemonList(): Flow<Result<PokemonListDao>>
    fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonDetailDao>>

}