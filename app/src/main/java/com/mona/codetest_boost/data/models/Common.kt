package com.mona.codetest_boost.data.models

import java.io.Serializable

data class Common(
    val name: String,
    val url: String
) : Serializable {
    override fun toString(): String {
        return "Common(name='$name', url='$url')"
    }

    fun pokemonId(): String {
        return url.substringAfter("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
    }

    fun pokemonImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonId()}.png"
    }

}