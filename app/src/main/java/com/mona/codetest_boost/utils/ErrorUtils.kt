package com.mona.codetest_boost.utils

import com.google.gson.GsonBuilder
import com.mona.codetest_boost.data.models.ApiError
import retrofit2.Response
import java.io.IOException

object ErrorUtils {

    fun parseError(response: Response<*>): ApiError {
        val gson = GsonBuilder().create()
        val error: ApiError

        try {
            error = gson.fromJson(response.errorBody()?.string(), ApiError::class.java)
        } catch (e: IOException) {
            return ApiError()
        }
        return error
    }
}
