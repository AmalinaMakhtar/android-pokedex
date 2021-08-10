package com.mona.codetest_boost.data.api

import com.mona.codetest_boost.data.models.General
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemon(): Response<General>

}
