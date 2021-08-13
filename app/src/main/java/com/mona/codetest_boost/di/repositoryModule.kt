package com.mona.codetest_boost.di

import com.mona.codetest_boost.data.api.PokemonApi
import com.mona.codetest_boost.data.repository.PokemonRepository
import com.mona.codetest_boost.data.repository.PokemonRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
    single { providePokemonRepository(get()) }

}