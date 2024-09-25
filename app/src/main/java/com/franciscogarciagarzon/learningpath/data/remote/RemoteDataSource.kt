package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokedexIndexListDto
import com.franciscogarciagarzon.learningpath.data.remote.model.toPokemonArticle
import com.franciscogarciagarzon.learningpath.data.remote.model.toPokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val pokemonService: PokemonService) : DatasourceAdapter {
    override suspend fun getPokedexIndexList(): Flow<Result<PokedexIndexList>> {
        val networkCallResultFlow: Flow<Result<PokedexIndexListDto>> = pokemonService.getPokemonList()
        lateinit var dtoResult: Result<PokedexIndexList>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> {
                        try {
                            Result.Success(result.value.toPokedexIndexList())
                        } catch (e: Exception) {
                            Result.Failure(e.message, e)
                        }
                    }

                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokedexIndexList>> { emit(dtoResult) }
    }


    override suspend fun getPokemonArticle(id: String): Flow<Result<PokemonArticle>> {
        val networkCallResultFlow: Flow<Result<PokemonArticleDto>> = pokemonService.getPokemonDetail(pokemonName = id)
        lateinit var dtoResult: Result<PokemonArticle>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> {
                        try {
                            Result.Success(result.value.toPokemonArticle())
                        } catch (e: Exception) {
                            Result.Failure(e.message, e)
                        }
                    }

                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokemonArticle>> { emit(dtoResult) }

    }
}