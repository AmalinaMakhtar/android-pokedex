package com.mona.codetest_boost.utils

sealed class AppResult<out T> {

    data class Success<out T>(val successData: T) : AppResult<T>()
    class Error(val message: String) : AppResult<Nothing>()

}