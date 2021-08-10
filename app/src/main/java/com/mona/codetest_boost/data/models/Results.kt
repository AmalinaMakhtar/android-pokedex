package com.mona.codetest_boost.data.models

import java.io.Serializable

data class Results(
    val name: String,
    val url: String
) : Serializable {
    override fun toString(): String {
        return "Results(name='$name', url='$url')"
    }
}