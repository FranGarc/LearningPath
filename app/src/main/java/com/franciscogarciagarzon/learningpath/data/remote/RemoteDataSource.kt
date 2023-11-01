package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.toPokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.toPokemonListDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val pokemonService: PokemonService) : DatasourceAdapter {
    override suspend fun getPokemonList(): Flow<PokemonListDto> = pokemonService.getPokemonList().map { list -> list.toPokemonListDto() }

    override suspend fun getPokemonDetail(id: String): Flow<PokemonDetailDto> = pokemonService.getPokemonDetail(pokemonName = id).map { detail -> detail.toPokemonDetailDto() }
}