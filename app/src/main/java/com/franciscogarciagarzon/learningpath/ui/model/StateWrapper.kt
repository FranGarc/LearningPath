package com.franciscogarciagarzon.learningpath.ui.model

sealed class StateWrapper<out T> {
    object Nothing : StateWrapper<kotlin.Nothing>()
    object Loading : StateWrapper<kotlin.Nothing>()
    data class Success<out S>(val value: S) : StateWrapper<S>()
    data class Error(val message: String) : StateWrapper<kotlin.Nothing>()
}