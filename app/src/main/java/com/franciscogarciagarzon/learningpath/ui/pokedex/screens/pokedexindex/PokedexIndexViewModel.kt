package com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokedexindex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.learningpath.DispatcherProvider
import com.franciscogarciagarzon.learningpath.domain.model.PokedexIndexList
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.franciscogarciagarzon.learningpath.domain.usecase.PokedexIndexListUseCase
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.StateWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexIndexViewModel @Inject constructor(
    private val getIndexListUseCase: PokedexIndexListUseCase,
    private val dispatcherProvider: DispatcherProvider,
    ) : ViewModel() {

    private val _uiState: MutableStateFlow<StateWrapper<PokedexIndexList>> = MutableStateFlow(
        StateWrapper.Nothing)
    val uiState = _uiState

    init {
        getPokedexIndexList()
    }

    fun resetUiState() {
        _uiState.value = StateWrapper.Nothing
    }

    fun getPokedexIndexList() {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.value = StateWrapper.Loading
            delay(1000L)

            Log.d("PokedexIndexViewModel", "getPokemonList launched")
            getIndexListUseCase().catch { e ->
                Log.e("PokedexIndexViewModel", "exception: ${e.javaClass} || message: ${e.message}", e)
            }.collect { result ->
                when (result) {
                    is Result.Success -> _uiState.value = StateWrapper.Success(value = result.value)
                    is Result.Failure -> {
                        _uiState.value = StateWrapper.Error(message = "Error: " + result.message)
                        Log.w("PokedexIndexViewModel", "getPokedexIndexList() error ${result.message}")
                    }
                }
            }
        }
    }
}