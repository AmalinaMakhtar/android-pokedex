package com.mona.codetest_boost.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mona.codetest_boost.data.models.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDao
}
