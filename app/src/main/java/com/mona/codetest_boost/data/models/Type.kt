package com.mona.codetest_boost.data.models

import com.mona.codetest_boost.R
import java.io.Serializable

data class Type(
    val slot: Int,
    val type: Common
) : Serializable {
    override fun toString(): String {
        return "Type(slot=$slot, type=$type)"
    }

    fun getTypeColor(): Int {
        when (type.name) {
            "bug","grass" -> return R.color.poke_green
            "electric" -> return R.color.poke_yellow
            "fighting", "fire", "fairy" -> return R.color.poke_red
            "ground", "normal", "dark", "rock", "steel" -> return R.color.poke_brown
            "dragon", "flying", "ghost", "poison", "psychic" -> return R.color.poke_purple
            "ice", "water" -> return R.color.poke_blue
        }
        return R.color.poke_yellow
    }
}
