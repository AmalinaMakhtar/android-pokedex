package com.mona.codetest_boost.data.models

import java.io.Serializable

data class Common(
    var name: String? = null,
    var url: String? = null
) : Serializable {
    override fun toString(): String {
        return "Common(name='$name', url='$url')"
    }

    fun pokemonId(): Int {
        return url?.substringAfter("https://pokeapi.co/api/v2/pokemon/")?.removeSuffix("/")?.toInt() ?: 0
    }

    fun pokemonImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonId()}.png"
    }

}