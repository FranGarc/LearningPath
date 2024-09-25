package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokedexIndexListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface PokemonService {
    fun getPokemonList(): Flow<Result<PokedexIndexListDto>>
    fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonArticleDto>>

}