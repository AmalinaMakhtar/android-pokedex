package com.mona.codetest_boost.data.models

import java.io.Serializable

data class General(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Common>
) : Serializable {
    override fun toString(): String {
        return "General(count=$count, next='$next', previous='$previous', results=$results)"
    }
}