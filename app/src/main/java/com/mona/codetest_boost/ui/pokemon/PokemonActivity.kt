package com.mona.codetest_boost.ui.pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mona.codetest_boost.R

class PokemonActivity : AppCompatActivity() {

    private var pokemonId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frg_pokemon)

        pokemonId = intent.extras?.getSerializable("POKEMON_ID") as String?

    }

}