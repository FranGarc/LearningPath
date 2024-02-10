package com.franciscogarciagarzon.learningpath.domain.model

sealed class Result<out T> {
    data class Success<out S>(val value: S) : Result<S>()
    data class Failure(
        val message: String?,
        val throwable: Throwable? = null
    ) : Result<Nothing>()
}