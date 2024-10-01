package com.franciscogarciagarzon.learningpath.data

import com.franciscogarciagarzon.learningpath.data.remote.model.PokedexIndexListDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface RemoteDataSourceAdapter {
    suspend fun getPokedexIndexList(): Flow<Result<PokedexIndexListDto>>
    suspend fun getPokemonArticle(id: String): Flow<Result<PokemonArticleDto>>
}