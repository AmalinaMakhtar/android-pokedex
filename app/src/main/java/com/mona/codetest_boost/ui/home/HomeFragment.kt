package com.mona.codetest_boost.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.FrgHomeBinding
import com.mona.codetest_boost.ui.ItemListener
import kotlinx.android.synthetic.main.frg_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), ItemListener {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var mViewDataBinding: FrgHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.frg_home, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        mViewDataBinding.viewModel = homeViewModel
        homeViewModel.getAllPokemon()
        homeViewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter.setPokemon(it)
            }
        })

    }

    private fun initUI() {
        adapter = HomeAdapter(context, this)
        rvPokemon.layoutManager = GridLayoutManager(activity, 2)
        rvPokemon.adapter = adapter
    }

    override fun onItemClick(id: String) {
        TODO("Not yet implemented")
    }
}
