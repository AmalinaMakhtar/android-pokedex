package com.mona.codetest_boost.data.repository

import android.content.Context
import com.mona.codetest_boost.data.api.PokemonApi
import com.mona.codetest_boost.data.models.General
import com.mona.codetest_boost.utils.AppResult
import com.mona.codetest_boost.utils.Utils

class PokemonRepositoryImpl(private val api: PokemonApi, private val context: Context) : PokemonRepository {

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

}