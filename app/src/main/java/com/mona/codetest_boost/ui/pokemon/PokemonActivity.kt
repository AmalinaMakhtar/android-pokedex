package com.mona.codetest_boost.ui.pokemon

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.databinding.ActPokemonBinding
import com.mona.codetest_boost.ui.home.HomeAdapter
import kotlinx.android.synthetic.main.act_pokemon.*
import kotlinx.android.synthetic.main.dlg_filter_card.*
import kotlinx.android.synthetic.main.frg_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonActivity : AppCompatActivity() {

    private var pokemonId: String? = ""
    private lateinit var binding: ActPokemonBinding
    private val pokemonViewModel by viewModel<PokemonViewModel>()
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActPokemonBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        pokemonId = intent.extras?.getSerializable("POKEMON_ID") as String?

        initToolbar()
        initUi()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initUi() {
        binding.viewModel = pokemonViewModel
        pokemonViewModel.getPokemonById(pokemonId.toString())
        pokemonViewModel.pokemon.observe(this@PokemonActivity, Observer {
            binding.pokemon = it
            adapter.setStats(it!!.stats)
        })

        adapter = PokemonAdapter(this@PokemonActivity)
        rvStats.layoutManager = LinearLayoutManager(this@PokemonActivity)
        rvStats.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}