package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDetailDao
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonServiceImpl @Inject constructor(private val pokeApi: PokeApi) : PokemonService {
    override fun getPokemonList(): Flow<PokemonListDao> {
        val call = pokeApi.getPokemonList()
        val response = call.execute().body() ?: PokemonListDao(count = 0, next = "", previous = "", results = emptyList())
        return flow {
            emit(response)
        }
    }

    override fun getPokemonDetail(pokemonName: String): Flow<PokemonDetailDao> {
        val call = pokeApi.getPokemonDetail(pokemonName)
        val response = call.execute().body()
        return flow {
            response?.let { emit(it) }
        }
    }
}