package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.data.remote.model.toPokemonDetail
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetail
import com.franciscogarciagarzon.learningpath.domain.model.PokemonList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.franciscogarciagarzon.learningpath.domain.model.toPokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val pokemonService: PokemonService) : DatasourceAdapter {
    override suspend fun getPokemonList(): Flow<Result<PokemonList>> {
        val networkCallResultFlow: Flow<Result<PokemonListDto>> = pokemonService.getPokemonList()
        lateinit var dtoResult: Result<PokemonList>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> {
                        try {
                            Result.Success(result.value.toPokemonList())
                        } catch (e: Exception) {
                            Result.Failure(e.message, e)
                        }
                    }

                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokemonList>> { emit(dtoResult) }
    }


    override suspend fun getPokemonDetail(id: String): Flow<Result<PokemonDetail>> {
        val networkCallResultFlow: Flow<Result<PokemonDetailDto>> = pokemonService.getPokemonDetail(pokemonName = id)
        lateinit var dtoResult: Result<PokemonDetail>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> {
                        try {
                            Result.Success(result.value.toPokemonDetail())
                        } catch (e: Exception) {
                            Result.Failure(e.message, e)
                        }
                    }

                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokemonDetail>> { emit(dtoResult) }

    }
}