package com.mona.codetest_boost.ui.home

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.FrgHomeBinding
import com.mona.codetest_boost.ui.ItemListener
import com.mona.codetest_boost.ui.favourite.FavouriteActivity
import com.mona.codetest_boost.ui.pokemon.PokemonActivity
import com.mona.codetest_boost.utils.hasInternet
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), ItemListener {

    private val model by viewModel<HomeViewModel>()
    private lateinit var binding: FrgHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrgHomeBinding.inflate(layoutInflater)

        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        retrieveData()
    }

    private fun retrieveData() {
        binding.viewModel = model
        model.getCachePokemon()
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

        val spacing = resources.getDimensionPixelSize(R.dimen.spacing_2x) / 2

        binding.rvPokemon.setPadding(spacing, spacing, spacing, spacing)
        binding.rvPokemon.clipToPadding = false
        binding.rvPokemon.clipChildren = false
        binding.rvPokemon.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })

        binding.fabSort.setOnClickListener {
            refreshList()
        }

        binding.btnFavourite.setOnClickListener {
            launchFavourite()
        }
    }

    private fun launchFavourite() {
        val intent = Intent(context, FavouriteActivity::class.java)
        context?.startActivity(intent)
    }

    override fun onItemClick(id: String) {
        if(!context?.hasInternet()!!) {
            return Snackbar.make(binding.parentContainer, "Opps! Please check your connection to proceed!", Snackbar.LENGTH_SHORT).show()
        }
        val intent = Intent(context, PokemonActivity::class.java).apply {
            putExtra("POKEMON_ID", id)
        }
        context?.startActivity(intent)
    }

    private fun refreshList() {
        model.getSortedPokemon()
        model.sortedList.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                adapter.setPokemon(it)
            }
        })
    }


}
