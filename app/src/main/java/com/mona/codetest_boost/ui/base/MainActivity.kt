package com.mona.codetest_boost.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mona.codetest_boost.R
import com.mona.codetest_boost.ui.favourite.FavouriteFragment
import com.mona.codetest_boost.ui.home.HomeFragment
import com.mona.codetest_boost.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CONTAINER_ID = R.id.frag_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHomeFragment()
    }

    private fun addHomeFragment() {
        replaceFragment(HomeFragment(), CONTAINER_ID)
    }

}