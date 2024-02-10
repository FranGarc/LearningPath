package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDetailDao
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDao
import com.franciscogarciagarzon.learningpath.data.remote.model.toPokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.franciscogarciagarzon.learningpath.domain.model.toPokemonListDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val pokemonService: PokemonService) : DatasourceAdapter {
    override suspend fun getPokemonList(): Flow<Result<PokemonListDto>> {
        val networkCallResultFlow: Flow<Result<PokemonListDao>> = pokemonService.getPokemonList()
        lateinit var dtoResult: Result<PokemonListDto>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> Result.Success(result.value.toPokemonListDto())
                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }

        }
        return flow<Result<PokemonListDto>> { emit(dtoResult) }
    }


    override suspend fun getPokemonDetail(id: String): Flow<Result<PokemonDetailDto>> {
        val networkCallResultFlow: Flow<Result<PokemonDetailDao>> = pokemonService.getPokemonDetail(pokemonName = id)
        lateinit var dtoResult: Result<PokemonDetailDto>
        networkCallResultFlow.collect { result ->
            dtoResult =
                when (result) {
                    is Result.Success -> Result.Success(result.value.toPokemonDetailDto())
                    is Result.Failure -> Result.Failure(result.message, result.throwable)
                }
        }
        return flow<Result<PokemonDetailDto>> { emit(dtoResult) }

    }
}