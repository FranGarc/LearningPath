package com.franciscogarciagarzon.learningpath.domain.usecase

import com.franciscogarciagarzon.learningpath.domain.RepositoryAdapter
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPokemonArticleUseCase @Inject constructor(private val datasource: RepositoryAdapter) : PokemonArticleUseCase {
    override suspend operator fun invoke(pokemonName: String): Flow<Result<PokemonArticle>> = datasource.getPokemonArticle(id = pokemonName)

}

interface PokemonArticleUseCase {
    suspend operator fun invoke(pokemonName: String): Flow<Result<PokemonArticle>>
}