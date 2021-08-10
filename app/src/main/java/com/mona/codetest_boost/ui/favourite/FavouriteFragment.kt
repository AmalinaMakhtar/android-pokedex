package com.mona.codetest_boost.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mona.codetest_boost.R
import com.mona.codetest_boost.databinding.FrgFavouriteBinding

class FavouriteFragment : Fragment() {

    private lateinit var mViewDataBinding: FrgFavouriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.frg_favourite, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }
}
