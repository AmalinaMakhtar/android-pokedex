package com.mona.codetest_boost.di

import com.mona.codetest_boost.ui.home.HomeViewModel
import com.mona.codetest_boost.ui.pokemon.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PokemonViewModel(get()) }
}