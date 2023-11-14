package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonDetailDao
import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonListDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon?")
    fun getPokemonList(
        @Query("limit") limit: Int = 1500,
        @Query("offset") offset: Int = 0,
    ): Call<PokemonListDao>

    @GET("pokemon/{name}")
    fun getPokemonDetail(@Path("name") name: String): Call<PokemonDetailDao>

}