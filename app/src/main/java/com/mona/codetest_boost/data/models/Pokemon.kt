package com.mona.codetest_boost.data.models

import java.io.Serializable

data class Pokemon(
    val base_experience: Int,
    val forms: List<Common>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Common,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) : Serializable {

    fun getImageUrl(): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    fun getPokemonWeight(): String = String.format("%.1f kg", weight.toFloat() / 10)
    fun getPokemonHeight(): String = String.format("%.1f m", height.toFloat() / 10)

    override fun toString(): String {
        return "Pokemon(base_experience=$base_experience, forms=$forms, height=$height, held_items=$held_items, id=$id, name='$name', order=$order, past_types=$past_types, species=$species, stats=$stats, types=$types, weight=$weight)"
    }

}