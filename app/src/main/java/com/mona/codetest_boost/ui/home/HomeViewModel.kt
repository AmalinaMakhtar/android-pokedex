package com.mona.codetest_boost.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mona.codetest_boost.data.models.Results
import com.mona.codetest_boost.data.repository.PokemonRepository
import com.mona.codetest_boost.utils.AppResult
import com.mona.codetest_boost.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: PokemonRepository) : ViewModel() {
    val showLoading = ObservableBoolean()
    val pokemonList = MutableLiveData<List<Results?>>()
    val showError = SingleLiveEvent<String?>()

    fun getAllPokemon() {
        showLoading.set(true)
        viewModelScope.launch {
            showLoading.set(false)
            when (val result = repo.getAllPokemon()) {
                is AppResult.Success -> {
                    pokemonList.value = result.successData.results
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.message
            }
        }
    }
}