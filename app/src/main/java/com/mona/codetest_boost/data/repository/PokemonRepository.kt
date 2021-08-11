package com.mona.codetest_boost.data.repository

import com.mona.codetest_boost.data.models.General
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.utils.AppResult

interface PokemonRepository {
    suspend fun getAllPokemon(): AppResult<General>
    suspend fun getPokemonById(id: String): AppResult<Pokemon>
}