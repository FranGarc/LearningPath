package com.franciscogarciagarzon.learningpath.data.remote

import android.util.Log
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class PokemonServiceImpl @Inject constructor(private val pokeApi: PokeApi) : PokemonService {
    override fun getPokemonList(): Flow<Result<PokemonListDto>> {
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

    override fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonArticleDto>> {
        val call = pokeApi.getPokemonDetail(pokemonName)
        val response = try {
            val execution = call.execute()
            if (execution.isSuccessful) {
                val body = execution.body()
                if (body == null) {
                    Result.Failure("Null Response")
                } else {
                    Log.d("PokemonServiceImpl", "getPokemonDetail() response: ${body}")

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
        }catch (e: JsonSyntaxException){
            Log.e("PokemonServiceImpl", "getPokemonDetail() JsonSyntaxException: ${e.message}")
            Result.Failure(e.message, e)
        }
        return flow {
            emit(response)
        }
    }
}