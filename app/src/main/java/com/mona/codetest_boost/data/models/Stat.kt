package com.mona.codetest_boost.data.models

import com.mona.codetest_boost.R
import java.io.Serializable

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: Common
) : Serializable {
    override fun toString(): String {
        return "Stat(base_stat=$base_stat, effort=$effort, stat=$stat)"
    }

    fun getColorStats(): Int {
        when (stat.name) {
            "hp" -> return R.color.poke_blue
            "attack" -> return R.color.poke_brown
            "defense" -> return R.color.poke_green
            "special-attack" -> return R.color.poke_red
            "special-defense" -> return R.color.poke_purple
            "speed" -> return R.color.poke_yellow
        }
        return R.color.black
    }

}
