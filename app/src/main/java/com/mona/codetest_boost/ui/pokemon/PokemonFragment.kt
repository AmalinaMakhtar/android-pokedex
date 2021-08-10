package com.mona.codetest_boost.ui.pokemon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.FrgPokemonBinding

class PokemonFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String) = PokemonFragment().apply {
            arguments = Bundle().apply {
                putString("pokemon_id", id)
            }
        }
    }

    private var pokemonId: String? = ""
    private lateinit var mViewDataBinding: FrgPokemonBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonId =  arguments?.getString("pokemon_id")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mViewDataBinding  = DataBindingUtil.inflate(inflater, R.layout.frg_pokemon, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }

}