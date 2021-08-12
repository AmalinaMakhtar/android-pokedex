package com.mona.codetest_boost.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mona.codetest_boost.data.models.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM POKEMON_TABLE WHERE id = :id")
    suspend fun getPokemonById(id: Int): List<Pokemon>

    @Query("SELECT * FROM POKEMON_TABLE")
    suspend fun getAllPokemon(): List<Pokemon>

    @Query("SELECT * FROM POKEMON_TABLE WHERE isFav = :isFavourite")
    suspend fun getFavPokemon(isFavourite: Boolean): List<Pokemon>

    @Query("UPDATE POKEMON_TABLE SET isFav = :isFavourite WHERE id = :id")
    suspend fun updatePokemon(id: String, isFavourite: Boolean)

}