package com.franciscogarciagarzon.learningpath.domain

import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetail
import com.franciscogarciagarzon.learningpath.domain.model.PokemonList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface DatasourceAdapter {
    suspend fun getPokemonList(): Flow<Result<PokemonList>>
    suspend fun getPokemonDetail(id: String): Flow<Result<PokemonDetail>>
}