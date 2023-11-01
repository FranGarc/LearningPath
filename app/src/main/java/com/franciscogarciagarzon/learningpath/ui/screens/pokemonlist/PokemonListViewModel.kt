package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getListUseCase: PokemonListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonListDto> = MutableStateFlow<PokemonListDto>(PokemonListDto(emptyList()))
    val uiState = _uiState

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("PokemonListViewModel", "getPokemonList launched")
            getListUseCase().flowOn(Dispatchers.IO).catch { e ->
                Log.e("PokemonListViewModel", "exception: ${e.message}", e)
            }.collect { pokemonList ->
                _uiState.value = pokemonList
            }
        }
    }
}