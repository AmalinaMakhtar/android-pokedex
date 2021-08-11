package com.mona.codetest_boost.data.models

import java.io.Serializable

data class Type(
    val slot: Int,
    val type: Common
) : Serializable{
    override fun toString(): String {
        return "Type(slot=$slot, type=$type)"
    }
}
