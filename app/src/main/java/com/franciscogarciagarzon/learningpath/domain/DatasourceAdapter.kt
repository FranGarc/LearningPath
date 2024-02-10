package com.franciscogarciagarzon.learningpath.domain

import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface DatasourceAdapter {
    suspend fun getPokemonList(): Flow<Result<PokemonListDto>>
    suspend fun getPokemonDetail(id: String): Flow<Result<PokemonDetailDto>>
}