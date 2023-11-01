package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: PokemonDetailUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow<PokemonDetailDto>(PokemonDetailDto())
    val uiState = _uiState
    fun getPokemonDetail(pokemonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("PokemonDetailViewModel", "getPokemonDetail launched with id: $pokemonId")
            getPokemonDetailUseCase(pokemonName = pokemonId).flowOn(Dispatchers.IO).catch { e ->
                Log.e("PokemonDetailViewModel", "exception: ${e.message}", e)
            }.collect { pokemonDetail ->
                _uiState.value = pokemonDetail
            }
        }
    }

}