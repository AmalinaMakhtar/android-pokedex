package com.mona.codetest_boost.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.FrgHomeBinding
import com.mona.codetest_boost.ui.ItemListener
import com.mona.codetest_boost.ui.pokemon.PokemonActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), ItemListener {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: FrgHomeBinding
    private lateinit var adapter: HomeAdapter
    private var customView: View? = null
    private var chipSortGroup: ChipGroup? = null
    private var btnConfirm: Button? = null
    private var dialog: AlertDialog? = null
    private var sortGroupChip: Chip? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrgHomeBinding.inflate(layoutInflater)

        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initSortDialog()
        retrieveData()
    }

    private fun retrieveData() {
        binding.viewModel = homeViewModel
        homeViewModel.getCachePokemon()
        homeViewModel.pokemonList.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                adapter.setPokemon(it)
            }
        })
    }

    private fun initUI() {
        adapter = HomeAdapter(context, this)
        binding.rvPokemon.layoutManager = GridLayoutManager(activity, 2)
        binding.rvPokemon.adapter = adapter

        binding.btnfilter.setOnClickListener {
            showSortDialog()
        }
    }

    override fun onItemClick(id: String) {
        val intent = Intent(context, PokemonActivity::class.java).apply {
            putExtra("POKEMON_ID", id)
        }
        context?.startActivity(intent)
    }

    override fun onFavItemClick(id: String, isSelect: Boolean) {
        homeViewModel.updateFavouritePokemon(id, isSelect)
    }

    private fun initSortDialog() {
        customView = LayoutInflater.from(context).inflate(R.layout.dlg_filter_card, null)
        chipSortGroup = customView!!.findViewById(R.id.chipSortGroup)
        btnConfirm = customView!!.findViewById(R.id.btnConfirm)
        dialog = AlertDialog.Builder(context)
            .setView(customView)
            .create()
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun showSortDialog() {
        chipSortGroup!!.setOnCheckedChangeListener { group, checkedId ->
            sortGroupChip = group.findViewById(checkedId)
        }

        btnConfirm!!.setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.show()
    }


}
