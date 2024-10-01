package com.franciscogarciagarzon.learningpath.di

import com.franciscogarciagarzon.learningpath.DefaultDispatcherProvider
import com.franciscogarciagarzon.learningpath.DispatcherProvider
import com.franciscogarciagarzon.learningpath.data.RemoteDataSourceAdapter
import com.franciscogarciagarzon.learningpath.data.Repository
import com.franciscogarciagarzon.learningpath.data.remote.PokeApi
import com.franciscogarciagarzon.learningpath.data.remote.PokemonService
import com.franciscogarciagarzon.learningpath.data.remote.PokemonServiceImpl
import com.franciscogarciagarzon.learningpath.data.remote.RemoteDataSource
import com.franciscogarciagarzon.learningpath.data.remote.RetrofitClient
import com.franciscogarciagarzon.learningpath.domain.RepositoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    private const val BASE_URL = "https://pokeapi.co/api/v2/"


    @Provides
    fun provideRemoteDataSource(pokemonService: PokemonService): RemoteDataSourceAdapter {
        return RemoteDataSource(pokemonService)
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource, dispatcherProvider: DispatcherProvider): RepositoryAdapter {
        return Repository(remoteDataSource, dispatcherProvider)
    }





}