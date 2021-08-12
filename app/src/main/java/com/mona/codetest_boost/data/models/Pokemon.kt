package com.mona.codetest_boost.data.models

import androidx.annotation.NonNull
import androidx.room.*
import com.mona.codetest_boost.utils.Converters
import java.io.Serializable

@Entity(tableName = "POKEMON_TABLE")
@TypeConverters(Converters::class)
data class Pokemon(
    @PrimaryKey
    @NonNull
    var id: Int? = 0,
    var base_experience: Int? = 0,
    var height: Int? = 0,
    var name: String? = null,
    var order: Int? = 0,
    @Ignore
    var stats: List<Stat>? = null,
    @Ignore
    var types: List<Type>? = null,
    var weight: Int? = 0,
    var isFav: Boolean? = false,
    var photoUrl: String? = null
) : Serializable {

    fun getImageUrl(): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    fun getPokemonWeight(): String = String.format("%.1f kg", weight!!.toFloat() / 10)
    fun getPokemonHeight(): String = String.format("%.1f m", height!!.toFloat() / 10)

    override fun toString(): String {
        return "Pokemon(id=$id, base_experience=$base_experience, height=$height, name=$name, order=$order, stats=$stats, types=$types, weight=$weight, isFav=$isFav, photoUrl=$photoUrl)"
    }
}