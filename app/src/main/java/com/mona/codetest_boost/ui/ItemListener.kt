package com.mona.codetest_boost.ui

interface ItemListener {
    fun onItemClick(id : String)
    fun onFavItemClick(id : String, isSelect: Boolean)
}