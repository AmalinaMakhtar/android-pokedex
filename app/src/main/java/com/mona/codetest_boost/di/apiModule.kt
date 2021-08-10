package com.mona.codetest_boost.di

import com.mona.codetest_boost.data.api.PokemonApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideCountriesApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }
    single { provideCountriesApi(get()) }

}