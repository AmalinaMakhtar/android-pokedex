package com.mona.codetest_boost.utils

import retrofit2.Response

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): AppResult.Error {
        val error = ErrorUtils.parseError(resp)
        return AppResult.Error(error.message)
    }

    fun <T : Any> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}