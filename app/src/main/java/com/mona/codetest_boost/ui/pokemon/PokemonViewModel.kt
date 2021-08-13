package com.mona.codetest_boost.ui.pokemon

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mona.codetest_boost.data.db.PokemonDao
import com.mona.codetest_boost.data.models.Pokemon
import com.mona.codetest_boost.data.repository.PokemonRepository
import com.mona.codetest_boost.utils.AppResult
import com.mona.codetest_boost.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class PokemonViewModel(private val repo: PokemonRepository, private val dao: PokemonDao) : ViewModel() {
    val showLoading = ObservableBoolean()
    val pokemon = MutableLiveData<Pokemon?>()
    val showError = SingleLiveEvent<String?>()
    val pokemonStatus = MutableLiveData<Boolean?>()

    fun getPokemonById(id: String) {
        showLoading.set(true)
        viewModelScope.launch {
            showLoading.set(false)
            when (val result = repo.getPokemonById(id)) {
                is AppResult.Success -> {
                    pokemon.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.message
            }
        }
    }

    fun updateFavouritePokemon(id: String, isSelect: Boolean) {
        viewModelScope.launch {
            dao.updatePokemon(id, isSelect)
        }
    }

    fun getFavouriteStatus(id: String) {
        viewModelScope.launch {
            pokemonStatus.value = dao.getFavPokemon(id)
        }
    }
}