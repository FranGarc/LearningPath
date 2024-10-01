package com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokemonarticle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.learningpath.DispatcherProvider
import com.franciscogarciagarzon.learningpath.domain.model.PokemonArticle
import com.franciscogarciagarzon.learningpath.domain.model.Result
import com.franciscogarciagarzon.learningpath.domain.usecase.PokemonArticleUseCase
import com.franciscogarciagarzon.learningpath.ui.pokedex.model.StateWrapper
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
class PokemonArticleViewModel @Inject constructor(
    private val getPokemonArticleUseCase: PokemonArticleUseCase,
    private val dispatcherProvider: DispatcherProvider,

    ) : ViewModel() {
    private val _tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val tabIndex: StateFlow<Int> = _tabIndex

    val tabs = listOf("About", "Base Stats")


    private var _uiState: MutableStateFlow<StateWrapper<PokemonArticle>> = MutableStateFlow(
        StateWrapper.Nothing)
    val uiState = _uiState
    fun getPokemonDetail(pokemonId: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.value = StateWrapper.Loading
            delay(1000L)

            Log.d("PokemonArticleViewModel", "getPokemonDetail launched with id: $pokemonId")
            getPokemonArticleUseCase(pokemonName = pokemonId).catch { e ->
                Log.e("PokemonArticleViewModel", "exception: ${e.message}", e)
            }.collect { result ->
                when (result) {
                    is Result.Success -> _uiState.value = StateWrapper.Success(value = result.value)
                    is Result.Failure -> {
                        _uiState.value = StateWrapper.Error(message = "Error: " + result.message)
                        Log.w("PokemonArticleViewModel", "getPokemonList() error ${result.message}")
                    }
                }
            }
        }
    }

    fun onEvent(userEvent: PokemonArticleUserEvent) {
        when (userEvent) {
            is PokemonArticleUserEvent.OnClickedTab -> updateTabIndex(userEvent.clickedIndex)
            is PokemonArticleUserEvent.OnSwipedTab -> updateTabIndexBasedOnSwipe(userEvent.isSwipeToTheLeft)
        }
    }

    fun resetUiStatae() {
        _uiState.value = StateWrapper.Nothing
    }

    fun updateTabIndexBasedOnSwipe(isSwipeToTheLeft: Boolean) {
        Log.d("PokemonArticleViewModel", "updateTabIndexBasedOnSwipe isSwipeToTheLeft: $isSwipeToTheLeft")

        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> {
                Math.floorMod(_tabIndex.value.plus(1), tabs.size)
            }

            false -> {
                Math.floorMod(_tabIndex.value.minus(1), tabs.size)
            }
        }
    }


    fun updateTabIndex(i: Int) {
        Log.d("PokemonArticleViewModel", "updateTabIndex: $i")

        viewModelScope.launch {
            _tabIndex.emit(i)
        }
    }

}

sealed class PokemonArticleUserEvent {
    class OnClickedTab(val clickedIndex: Int) : PokemonArticleUserEvent()
    class OnSwipedTab(val isSwipeToTheLeft: Boolean) : PokemonArticleUserEvent()
}