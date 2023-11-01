package com.franciscogarciagarzon.learningpath.di

import com.franciscogarciagarzon.learningpath.data.remote.PokeApi
import com.franciscogarciagarzon.learningpath.data.remote.PokemonService
import com.franciscogarciagarzon.learningpath.data.remote.PokemonServiceImpl
import com.franciscogarciagarzon.learningpath.data.remote.RemoteDataSource
import com.franciscogarciagarzon.learningpath.data.remote.RetrofitClient
import com.franciscogarciagarzon.learningpath.domain.DatasourceAdapter
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
    fun provideHttpLoger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(httpLogger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLogger).build()
    }


    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofitClient(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(converterFactory).client(okHttpClient).build()
    }


    @Provides
    fun providePokemonApi(): PokeApi {
        return RetrofitClient.pokeService
    }

    @Provides
    fun providePokemonService(api: PokeApi): PokemonService {
        return PokemonServiceImpl(api)
    }

    @Provides
    fun provideRemoteDataSource(pokemonService: PokemonService): DatasourceAdapter {
        return RemoteDataSource(pokemonService)
    }


}