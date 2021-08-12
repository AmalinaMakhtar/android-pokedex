package com.mona.codetest_boost.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mona.codetest_boost.R
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.databinding.ItemPokemonBinding
import com.mona.codetest_boost.ui.ItemListener

class HomeAdapter(private val context: Context?, private val listener: ItemListener) : RecyclerView.Adapter<HomeAdapter.DashboardHolder>() {

    var pokemonList: List<Pokemon?> = ArrayList()

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

    fun setPokemon(pokemon: List<Pokemon?>) {
        this.pokemonList = pokemon
        notifyDataSetChanged()
    }

    inner class DashboardHolder(private val viewBinding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val pokemonObj = pokemonList[position]
            viewBinding.pokemon = pokemonObj
            viewBinding.txtPokemonName.text = pokemonObj!!.name?.uppercase() ?: "Unknown"

            Glide
                .with(context!!)
                .load(pokemonObj.photoUrl)
                .centerCrop()
                .into(viewBinding.imgPokemon)

            viewBinding.pokemonId = pokemonObj.id.toString()
            viewBinding.itemCallback = listener

            viewBinding.imgFavourite.isSelected = pokemonObj.isFav!!

            viewBinding.imgFavourite.setOnClickListener {
                viewBinding.imgFavourite.isSelected = !viewBinding.imgFavourite.isSelected
                listener.onFavItemClick(pokemonObj.id.toString(), viewBinding.imgFavourite.isSelected)
            }

        }
    }

}