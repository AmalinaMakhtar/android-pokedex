package com.mona.codetest_boost.di

import android.content.Context
import com.mona.codetest_boost.data.api.PokemonApi
import com.mona.codetest_boost.data.repository.PokemonRepository
import com.mona.codetest_boost.data.repository.PokemonRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun providePokemonRepository(api: PokemonApi, context: Context): PokemonRepository {
        return PokemonRepositoryImpl(api, context)
    }
    single { providePokemonRepository(get(), androidContext()) }

}