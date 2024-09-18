package com.franciscogarciagarzon.learningpath.domain.usecase

import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetail
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPokemonDetailUseCase @Inject constructor(private val datasource: DatasourceAdapter) : PokemonDetailUseCase {
    override suspend operator fun invoke(pokemonName: String): Flow<Result<PokemonDetail>> = datasource.getPokemonDetail(id = pokemonName)

}

interface PokemonDetailUseCase {
    suspend operator fun invoke(pokemonName: String): Flow<Result<PokemonDetail>>
}