package com.mona.codetest_boost.ui.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mona.codetest_boost.R
import com.mona.codetest_boost.data.models.Stat
import com.mona.codetest_boost.databinding.ItemStatBinding

class PokemonAdapter(private val context: Context) : RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    var statList: List<Stat?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val viewBinding: ItemStatBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_stat, parent, false
        )
        return PokemonHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return statList.size
    }

    fun setStats(stats: List<Stat?>) {
        this.statList = stats
        notifyDataSetChanged()
    }

    inner class PokemonHolder(private val viewBinding: ItemStatBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val statObj = statList[position]
            viewBinding.pokemonStats = statObj
            if (statObj != null) {
                viewBinding.lblStat.text = statObj.stat.name
                viewBinding.progressStats.progress = statObj.base_stat
                viewBinding.progressStats.setIndicatorColor(context.getColor(statObj.getColorStats()))
            }
        }

    }
}