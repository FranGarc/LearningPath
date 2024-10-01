package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.RemoteDataSourceAdapter
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokedexIndexListDto
import com.franciscogarciagarzon.learningpath.data.remote.model.toPokemonArticle
import com.franciscogarciagarzon.learningpath.data.remote.model.toPokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.RepositoryAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val pokemonService: PokemonService) :
    RemoteDataSourceAdapter {
    override suspend fun getPokedexIndexList(): Flow<Result<PokedexIndexListDto>> {
        val networkCallResultFlow: Flow<Result<PokedexIndexListDto>> = pokemonService.getPokemonList()
        lateinit var dtoResult: Result<PokedexIndexListDto>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> {
                        try {
                            Result.Success(result.value
                            //    .toPokedexIndexList()
                            )
                        } catch (e: Exception) {
                            Result.Failure(e.message, e)
                        }
                    }

                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokedexIndexListDto>> { emit(dtoResult) }.flowOn(Dispatchers.IO)
    }


    override suspend fun getPokemonArticle(id: String): Flow<Result<PokemonArticleDto>> {
        val networkCallResultFlow: Flow<Result<PokemonArticleDto>> = pokemonService.getPokemonDetail(pokemonName = id)
        lateinit var dtoResult: Result<PokemonArticleDto>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> {
                        try {
                            Result.Success(result.value)
                        } catch (e: Exception) {
                            Result.Failure(e.message, e)
                        }
                    }

                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokemonArticleDto>> { emit(dtoResult) }

    }
}