package com.franciscogarciagarzon.learningpath.domain

import com.franciscogarciagarzon.learningpath.domain.usecase.GetPokemonDetailUseCase
import com.franciscogarciagarzon.learningpath.domain.usecase.GetPokemonListUseCase
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonDetailUseCase
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun provideGetPokemonListUseCase(datasource: DatasourceAdapter): PokemonListUseCase {
        return GetPokemonListUseCase(datasource)
    }

    @Provides
    fun provideGetPokemonDetailUseCase(datasource: DatasourceAdapter): PokemonDetailUseCase {
        return GetPokemonDetailUseCase(datasource)
    }
}