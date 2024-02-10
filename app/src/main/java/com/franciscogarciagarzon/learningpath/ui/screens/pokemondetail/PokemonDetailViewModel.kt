package com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.learningpath.domain.model.PokemonDetailDto
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonDetailUseCase
import com.franciscogarciagarzon.learningpath.ui.model.StateWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: PokemonDetailUseCase
) : ViewModel() {
    private val _tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val tabIndex: StateFlow<Int> = _tabIndex

    val tabs = listOf("About", "Base Stats")


    private var _uiState: MutableStateFlow<StateWrapper<PokemonDetailDto>> = MutableStateFlow(StateWrapper.Nothing)
    val uiState = _uiState
    fun getPokemonDetail(pokemonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = StateWrapper.Loading
            delay(1000L)

            Log.d("PokemonDetailViewModel", "getPokemonDetail launched with id: $pokemonId")
            getPokemonDetailUseCase(pokemonName = pokemonId).flowOn(Dispatchers.IO).catch { e ->
                Log.e("PokemonDetailViewModel", "exception: ${e.message}", e)
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

    fun resetUiStatae() {
        _uiState.value = StateWrapper.Nothing
    }

    fun updateTabIndexBasedOnSwipe(isSwipeToTheLeft: Boolean) {
        Log.d("PokemonDetailViewModel", "updateTabIndexBasedOnSwipe isSwipeToTheLeft: $isSwipeToTheLeft")

        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> Math.floorMod(_tabIndex.value.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value.minus(1), tabs.size)
        }
    }


    fun updateTabIndex(i: Int) {
        Log.d("PokemonDetailViewModel", "updateTabIndex: $i")

        viewModelScope.launch {
            _tabIndex.emit(i)
        }
    }

}