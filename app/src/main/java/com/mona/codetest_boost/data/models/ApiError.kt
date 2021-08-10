package com.mona.codetest_boost.data.models

data class ApiError(
    val status: kotlin.String,
    val statusCode: kotlin.Int,
    val code: kotlin.String,
    val message: kotlin.String,
) {
    constructor() : this("", 0, "", "")
}