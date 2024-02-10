package com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.learningpath.domain.model.PokemonListDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonListUseCase
import com.franciscogarciagarzon.learningpath.ui.model.StateWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getListUseCase: PokemonListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<StateWrapper<PokemonListDto>> = MutableStateFlow(StateWrapper.Nothing)
    val uiState = _uiState

    init {
        getPokemonList()
    }

    fun resetUiStatae() {
        _uiState.value = StateWrapper.Nothing
    }

    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = StateWrapper.Loading
            delay(1000L)

            Log.d("PokemonListViewModel", "getPokemonList launched")
            getListUseCase().flowOn(Dispatchers.IO).catch { e ->
                Log.e("PokemonListViewModel", "exception: ${e.message}", e)
//            }.collect { pokemonList ->
            }.collect { result ->
                when (result) {
                    is Result.Success -> _uiState.value = StateWrapper.Success(value = result.value)
                    is Result.Failure -> {
                        _uiState.value = StateWrapper.Error(message = "Error: " + result.message)
                        Log.w("PokemonListViewModel", "getPokemonList() error ${result.message}")
                    }
                }

            }
        }
    }
}