package com.franciscogarciagarzon.learningpath.domain.usecase

import android.util.Log
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokedexIndexListUseCase
@Inject
constructor(private val datasource: DatasourceAdapter) : PokedexIndexListUseCase {
    override suspend operator fun invoke(): Flow<Result<PokedexIndexList>> {
        lateinit var datasourceResponse: Result<PokedexIndexList>
        datasource.getPokedexIndexList().collect { result -> datasourceResponse = result }
        lateinit var useCaseResponse: Result<PokedexIndexList>
        when (datasourceResponse) {
            is Result.Failure -> {
                val exception = (datasourceResponse as Result.Failure).throwable
                Log.e("GetPokemonListUseCase", "exception: ${exception?.javaClass?.simpleName}")
                exception?.printStackTrace()
                useCaseResponse = datasourceResponse
            }

            is Result.Success -> {
                val payload = (datasourceResponse as Result.Success<PokedexIndexList>).value
                val listSize = payload.pokedexIndexItems.size

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

interface PokedexIndexListUseCase {
    suspend operator fun invoke(): Flow<Result<PokedexIndexList>>
}