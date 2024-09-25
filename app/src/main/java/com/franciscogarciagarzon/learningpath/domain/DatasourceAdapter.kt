package com.franciscogarciagarzon.learningpath.domain

import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface DatasourceAdapter {
    suspend fun getPokemonList(): Flow<Result<PokedexIndexList>>
    suspend fun getPokemonDetail(id: String): Flow<Result<PokemonArticle>>
}