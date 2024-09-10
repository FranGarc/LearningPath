package com.franciscogarciagarzon.learningpath.domain.usecase

import android.util.Log
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonListUseCase
@Inject
constructor(private val datasource: DatasourceAdapter) : PokemonListUseCase {
    override suspend operator fun invoke(): Flow<Result<PokemonListDto>> {
        lateinit var datasourceResponse: Result<PokemonListDto>
        datasource.getPokemonList().collect { result -> datasourceResponse = result }
        lateinit var useCaseResponse: Result<PokemonListDto>
        when (datasourceResponse) {
            is Result.Failure -> {
                val exception = (datasourceResponse as Result.Failure).throwable
                Log.e("GetPokemonListUseCase", "exception: ${exception?.javaClass?.simpleName}")
                exception?.printStackTrace()
                useCaseResponse = datasourceResponse
            }

            is Result.Success -> {
                val payload = (datasourceResponse as Result.Success<PokemonListDto>).value
                val listSize = payload.pokemons.size

                useCaseResponse = if (listSize > 0) {
                    Result.Success(payload)
                } else {
                    Result.Failure(message = "EMPTY LIST")
                }
            }
        }

        return flow { emit(useCaseResponse) }
    }

}

interface PokemonListUseCase {
    suspend operator fun invoke(): Flow<Result<PokemonListDto>>
}