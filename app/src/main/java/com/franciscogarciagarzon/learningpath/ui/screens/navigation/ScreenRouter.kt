package com.franciscogarciagarzon.learningpath.ui.screens.navigation

sealed class ScreenRouter(val route: String) {
    object PokemonList : ScreenRouter("main")
    object PokemonDetail : ScreenRouter("detail/{pokemonName}") {
        fun createRoute(pokemonName: String) = "detail/${pokemonName}"
    }
}
