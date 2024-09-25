package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface PokemonService {
    fun getPokemonList(): Flow<Result<PokemonListDto>>
    fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonArticleDto>>

}