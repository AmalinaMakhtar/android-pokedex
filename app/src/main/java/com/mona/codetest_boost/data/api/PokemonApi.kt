package com.mona.codetest_boost.data.api

import com.mona.codetest_boost.data.models.General
import com.mona.codetest_boost.data.models.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemon(): Response<General>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: String): Response<Pokemon>
}
