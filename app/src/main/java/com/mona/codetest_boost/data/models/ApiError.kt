package com.mona.codetest_boost.data.models

data class ApiError(
    val status: String,
    val statusCode: Int,
    val code: String,
    val message: String,
) {
    constructor() : this("", 0, "", "")
}