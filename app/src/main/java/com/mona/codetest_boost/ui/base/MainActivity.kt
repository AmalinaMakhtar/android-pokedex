package com.mona.codetest_boost.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mona.codetest_boost.R
import com.mona.codetest_boost.ui.favourite.FavouriteFragment
import com.mona.codetest_boost.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CONTAINER_ID = R.id.frag_container
    }

    private val frgHome by lazy { HomeFragment() }
    private val frgFavourite by lazy { FavouriteFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener {
            toggleView(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }

        setDefaultNavigation()
        toggleView(R.id.menu_home)

    }

    private fun setDefaultNavigation() {
        bottomNavigation.inflateMenu(R.menu.bottom_navigation)
        supportFragmentManager.beginTransaction()
            .add(CONTAINER_ID, frgHome)
            .add(CONTAINER_ID, frgFavourite)
            .commit()
    }

    private fun toggleView(menu: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        when (menu) {
            R.id.menu_home -> {
                transaction.show(frgHome).hide(frgFavourite).commit()
            }
            R.id.menu_fav -> {
                transaction.show(frgFavourite).hide(frgHome).commit()
            }
        }
    }

}