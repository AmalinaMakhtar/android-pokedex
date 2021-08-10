package com.mona.codetest_boost.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mona.codetest_boost.R
import com.mona.codetest_boost.ui.home.HomeFragment
import com.mona.codetest_boost.utils.replaceFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addDashboardFragment()
    }

    private fun addDashboardFragment() {
        replaceFragment(HomeFragment(),
            R.id.frag_container
        )
    }

}