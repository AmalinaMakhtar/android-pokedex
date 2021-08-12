package com.mona.codetest_boost.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mona.codetest_boost.databinding.FrgFavouriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

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
    }

    private fun retrieveData() {
        model.getFavouriteList()
        model.pokemonList.observe(viewLifecycleOwner, {
            Log.d("[?]", "retrieveData: +++++${it.size}")
        })
    }
}
