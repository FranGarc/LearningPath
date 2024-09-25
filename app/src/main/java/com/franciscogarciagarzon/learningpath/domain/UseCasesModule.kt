package com.franciscogarciagarzon.learningpath.domain

import com.franciscogarciagarzon.learningpath.domain.usecase.GetPokemonArticleUseCase
import com.franciscogarciagarzon.learningpath.domain.usecase.GetPokedexIndexListUseCase
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonArticleUseCase
import com.franciscogarciagarzon.learningpath.domain.usecase.PokedexIndexListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun provideGetPokedexIndexListUseCase(datasource: DatasourceAdapter): PokedexIndexListUseCase {
        return GetPokedexIndexListUseCase(datasource)
    }

    @Provides
    fun provideGetPokemonArticleUseCase(datasource: DatasourceAdapter): PokemonArticleUseCase {
        return GetPokemonArticleUseCase(datasource)
    }
}