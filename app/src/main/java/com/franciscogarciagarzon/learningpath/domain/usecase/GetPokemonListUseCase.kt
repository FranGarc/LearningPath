package com.franciscogarciagarzon.learningpath.domain.usecase

import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val datasource: DatasourceAdapter) : PokemonListUseCase {
    override suspend operator fun invoke(): Flow<Result<PokemonListDto>> = datasource.getPokemonList()

}

interface PokemonListUseCase {
    suspend operator fun invoke(): Flow<Result<PokemonListDto>>
}