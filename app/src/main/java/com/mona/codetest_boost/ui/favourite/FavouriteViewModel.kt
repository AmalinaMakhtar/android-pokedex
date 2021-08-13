package com.mona.codetest_boost.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mona.codetest_boost.data.db.PokemonDao
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class FavouriteViewModel(private val dao: PokemonDao) : ViewModel() {
    val pokemonList = MutableLiveData<List<Pokemon?>>()
    val showError = SingleLiveEvent<String?>()

    fun getFavouriteList() {
        viewModelScope.launch {
            val list = dao.getFavouriteList(true)
            if (list.isNullOrEmpty()) {
                showError.value = "Seems like there's no data available right now. Please try again"
            } else {
                showError.value = null
            }
            pokemonList.value = list
        }
    }

}