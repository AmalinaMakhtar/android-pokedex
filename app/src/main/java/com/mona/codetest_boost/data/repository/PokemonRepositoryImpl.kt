package com.mona.codetest_boost.data.repository

import com.mona.codetest_boost.data.api.PokemonApi
import com.mona.codetest_boost.data.models.General
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.utils.AppResult
import com.mona.codetest_boost.utils.Utils

class PokemonRepositoryImpl(private val api: PokemonApi) : PokemonRepository {

    override suspend fun getAllPokemon(): AppResult<General> {
        return try {
            val response = api.getAllPokemon()
            if (response.isSuccessful) {
                Utils.handleSuccess(response)
            } else {
                Utils.handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e.message.toString())
        }
    }

    override suspend fun getPokemonById(id: String): AppResult<Pokemon> {
        return try {
            val response = api.getPokemonById(id)
            if (response.isSuccessful) {
                Utils.handleSuccess(response)
            } else {
                Utils.handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e.message.toString())
        }
    }

}