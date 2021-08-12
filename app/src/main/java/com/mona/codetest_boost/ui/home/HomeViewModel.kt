package com.mona.codetest_boost.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mona.codetest_boost.R
import com.mona.codetest_boost.data.db.PokemonDao
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.data.repository.PokemonRepository
import com.mona.codetest_boost.utils.AppResult
import com.mona.codetest_boost.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: PokemonRepository, private val dao: PokemonDao) : ViewModel() {
    val showLoading = ObservableBoolean()
    val pokemonList = MutableLiveData<List<Pokemon?>>()
    val sortedList = MutableLiveData<List<Pokemon?>>()
    val showError = SingleLiveEvent<String?>()
    private val isDescending = SingleLiveEvent<Boolean?>()

    init {
        getLatestPokemon()
    }

    private fun getLatestPokemon() {
        showLoading.set(true)
        viewModelScope.launch {
            showLoading.set(false)
            when (val result = repo.getAllPokemon()) {
                is AppResult.Success -> {
                    val pokemons = result.successData.results
                    pokemons.forEach {
                        dao.addPokemon(Pokemon(id = it.pokemonId(), photoUrl = it.pokemonImage(), name = it.name))
                    }
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.message
            }
        }
    }

    fun getCachePokemon() {
        viewModelScope.launch {
            val list = dao.getAllPokemon()
            if (list.isNullOrEmpty()) {
                showError.value = "Seems like there's no data available right now. Please try again"
            } else {
                showError.value = null
            }
            pokemonList.value = list
        }
    }

    fun updateFavouritePokemon(id: String, isSelect: Boolean) {
        viewModelScope.launch {
            dao.updatePokemon(id, isSelect)
        }
    }

    fun getSortedPokemon() {
        viewModelScope.launch {
            sortedList.value = if(isDescending.value == true) {
                isDescending.value = false
                pokemonList.value?.sortedBy {
                    it?.name
                }
            } else {
                isDescending.value = true
                pokemonList.value?.sortedByDescending {
                    it?.name
                }
            }
        }
    }

}