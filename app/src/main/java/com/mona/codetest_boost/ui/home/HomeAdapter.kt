package com.mona.codetest_boost.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mona.codetest_boost.R
import com.mona.codetest_boost.data.models.Results
import com.mona.codetest_boost.databinding.ItemPokemonBinding
import com.mona.codetest_boost.ui.ItemListener

class HomeAdapter(private val context: Context?, private val listener: ItemListener) : RecyclerView.Adapter<HomeAdapter.DashboardHolder>() {

    var pokemonList: List<Results?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val viewBinding: ItemPokemonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_pokemon, parent, false
        )
        return DashboardHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setPokemon(pokemon: List<Results?>) {
        this.pokemonList = pokemon
        notifyDataSetChanged()
    }

    inner class DashboardHolder(private val viewBinding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val pokemonObj = pokemonList[position]
            viewBinding.pokemon = pokemonObj
            viewBinding.txtPokemonName.text = pokemonObj!!.name.capitalize()

            Glide
                .with(context!!)
                .load(pokemonObj.pokemonImage())
                .centerCrop()
                .into(viewBinding.imgPokemon)

            viewBinding.pokemonId = pokemonObj.pokemonId()
            viewBinding.itemCallback = listener
        }
    }

}