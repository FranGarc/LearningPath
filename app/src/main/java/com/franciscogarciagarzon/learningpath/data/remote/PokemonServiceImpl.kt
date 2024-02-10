package com.franciscogarciagarzon.learningpath.data.remote

import android.util.Log
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDetailDao
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDao
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class PokemonServiceImpl @Inject constructor(private val pokeApi: PokeApi) : PokemonService {
    override fun getPokemonList(): Flow<Result<PokemonListDao>> {
        val call = pokeApi.getPokemonList()
        val response = try {
            val execution = call.execute()
            if (execution.isSuccessful) {
                val body = execution.body()
                if (body == null) {
                    Result.Failure("Null Response")
                } else {
                    Result.Success(body)
                }

            } else {
                val error = execution.errorBody()
                Log.d("PokemonServiceImpl", "getPokemonList() response: $error")
                Result.Failure(error.toString())
            }
        } catch (e: IOException) {
            Log.e("PokemonServiceImpl", "getPokemonList() exception: ${e.message}")
            Result.Failure(e.message, e)
        }
        return flow {
            emit(response)
        }
    }

    override fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonDetailDao>> {
        val call = pokeApi.getPokemonDetail(pokemonName)
        val response = try {
            val execution = call.execute()
            if (execution.isSuccessful) {
                val body = execution.body()
                if (body == null) {
                    Result.Failure("Null Response")
                } else {
                    Result.Success(body)
                }
            } else {
                val error = execution.errorBody()
                Log.d("PokemonServiceImpl", "getPokemonDetail() response: ${error}")
                Result.Failure(error.toString())
            }

        } catch (e: IOException) {
            Log.e("PokemonServiceImpl", "getPokemonDetail() exception: ${e.message}")
            Result.Failure(e.message, e)
        }
        return flow {
            emit(response)
        }
    }
}