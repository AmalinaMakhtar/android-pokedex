package com.mona.codetest_boost.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mona.codetest_boost.databinding.FrgFavouriteBinding
import com.mona.codetest_boost.ui.ItemListener
import com.mona.codetest_boost.ui.home.HomeAdapter
import com.mona.codetest_boost.ui.pokemon.PokemonActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment(), ItemListener {

    private lateinit var adapter: HomeAdapter
    private lateinit var binding: FrgFavouriteBinding
    private val model by viewModel<FavouriteViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrgFavouriteBinding.inflate(layoutInflater)

        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveData()
        initUI()
    }

    private fun retrieveData() {
        model.getFavouriteList()
        model.pokemonList.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                adapter.setPokemon(it)
            }
        })
    }

    private fun initUI() {
        adapter = HomeAdapter(context, this)
        binding.rvPokemon.layoutManager = GridLayoutManager(activity, 2)
        binding.rvPokemon.adapter = adapter

    }

    override fun onItemClick(id: String) {
        val intent = Intent(context, PokemonActivity::class.java).apply {
            putExtra("POKEMON_ID", id)
        }
        context?.startActivity(intent)
    }

    override fun onFavItemClick(id: String, isSelect: Boolean) {
        //todo
    }
}
