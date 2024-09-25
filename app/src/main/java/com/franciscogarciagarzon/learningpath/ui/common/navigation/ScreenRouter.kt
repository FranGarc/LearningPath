package com.franciscogarciagarzon.learningpath.ui.common.navigation

sealed class ScreenRouter(val route: String) {
    object PokedexIndex : ScreenRouter("main")
    object PokemonArticle : ScreenRouter("article/{pokemonName}") {
        fun createRoute(pokemonName: String) = "article/${pokemonName}"
    }
}
