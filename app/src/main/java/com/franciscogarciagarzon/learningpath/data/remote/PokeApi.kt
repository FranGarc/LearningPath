package com.franciscogarciagarzon.learningpath.data.remote

import com.franciscogarciagarzon.learningpath.data.remote.model.PokemonArticleDto
import com.franciscogarciagarzon.learningpath.data.remote.model.PokedexIndexListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon?")
    fun getPokedexIndexList(
        @Query("limit") limit: Int = 1500,
        @Query("offset") offset: Int = 0,
    ): Call<PokedexIndexListDto>

    @GET("pokemon/{name}")
    fun getPokemonArticle(@Path("name") name: String): Call<PokemonArticleDto>

}