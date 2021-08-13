package com.mona.codetest_boost.ui.favourite

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.ActFavouriteBinding
import com.mona.codetest_boost.ui.ItemListener
import com.mona.codetest_boost.ui.home.HomeAdapter
import com.mona.codetest_boost.ui.pokemon.PokemonActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteActivity : AppCompatActivity(), ItemListener {

    private lateinit var adapter: HomeAdapter
    private lateinit var binding: ActFavouriteBinding
    private val model by viewModel<FavouriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActFavouriteBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initToolbar()
        initUI()
        retrieveData()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationIconTint(resources.getColor(R.color.white))
    }

    private fun retrieveData() {
        binding.viewModel = model

        model.getFavouriteList()
        model.pokemonList.observe(this@FavouriteActivity, {
            if (it != null && it.isNotEmpty()) {
                adapter.setPokemon(it)
            }
        })
    }

    private fun initUI() {
        adapter = HomeAdapter(this, this)
        binding.rvPokemon.layoutManager = GridLayoutManager(this, 2)
        binding.rvPokemon.adapter = adapter

        val spacing = resources.getDimensionPixelSize(R.dimen.spacing_2x) / 2

        binding.rvPokemon.setPadding(spacing, spacing, spacing, spacing)
        binding.rvPokemon.clipToPadding = false
        binding.rvPokemon.clipChildren = false
        binding.rvPokemon.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })
    }

    override fun onItemClick(id: String) {
        val intent = Intent(this, PokemonActivity::class.java).apply {
            putExtra("POKEMON_ID", id)
        }
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
