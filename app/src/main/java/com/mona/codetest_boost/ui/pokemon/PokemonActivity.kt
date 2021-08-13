package com.mona.codetest_boost.ui.pokemon

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.ActPokemonBinding
import kotlinx.android.synthetic.main.act_pokemon.*
import kotlinx.android.synthetic.main.frg_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonActivity : AppCompatActivity() {

    private var pokemonId: String? = ""
    private lateinit var binding: ActPokemonBinding
    private val model by viewModel<PokemonViewModel>()
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActPokemonBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        pokemonId = intent.extras?.getSerializable("POKEMON_ID") as String?

        initToolbar()
        initUi()
        retrieveData()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationIconTint(resources.getColor(R.color.white))
    }

    private fun initUi() {
        adapter = PokemonAdapter(this@PokemonActivity)
        binding.rvStats.layoutManager = LinearLayoutManager(this@PokemonActivity)
        binding.rvStats.adapter = adapter

        binding.imgFavourite.setOnClickListener {
            binding.imgFavourite.isSelected = !binding.imgFavourite.isSelected
            pokemonId?.let { it1 -> model.updateFavouritePokemon(it1, binding.imgFavourite.isSelected) }
        }
    }

    private fun retrieveData() {
        binding.viewModel = model
        model.getPokemonById(pokemonId.toString())
        model.pokemon.observe(this@PokemonActivity, Observer {
            binding.pokemon = it
            adapter.setStats(it!!.stats)
            val typeColor = it.types?.first()?.getTypeColor() ?: R.color.poke_red
            binding.containerImage.setBackgroundColor(getColor(typeColor))
            binding.toolbar.setBackgroundColor(getColor(typeColor))

            Glide.with(this@PokemonActivity)
                .load(it.getImageUrl())
                .centerCrop()
                .into(binding.imgPokemon)
        })

        model.getFavouriteStatus(pokemonId.toString())
        model.pokemonStatus.observe(this@PokemonActivity, Observer {
            binding.imgFavourite.isSelected = it ?: false
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}