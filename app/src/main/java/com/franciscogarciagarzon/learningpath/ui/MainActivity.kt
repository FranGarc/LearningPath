package com.franciscogarciagarzon.learningpath.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.franciscogarciagarzon.learningpath.ui.common.navigation.ScreenRouter
import com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokemonarticle.PokemonArticle
import com.franciscogarciagarzon.learningpath.ui.pokedex.screens.pokedexindex.PokedexIndexPage
import com.franciscogarciagarzon.learningpath.ui.common.theme.LearningPathTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningPathTheme {
                Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = ScreenRouter.PokedexIndex.route
                    ) {
                        composable(ScreenRouter.PokedexIndex.route) {
                            PokedexIndexPage(showPokemonArticle = { pokemonName ->
                                Log.d("MainActivity", "navigation lambda showPokemonDetail ($pokemonName)")
                                navController.navigate(
                                    route = ScreenRouter.PokemonArticle.createRoute(pokemonName)
                                )
                            }, favNavigation = { navController.navigate(route = ScreenRouter.PokemonArticle.createRoute("ogerpon-cornerstone-mask")) })
                        }
                        composable(route = ScreenRouter.PokemonArticle.route) { backStackEntry ->
                            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                            requireNotNull(pokemonName)
                            PokemonArticle(id = pokemonName, navigateUp = {
                                navController.popBackStack()
                            })
                            Icons.Default.Favorite
                            Icons.Default.Home
                        }

                    }
                }
            }
        }
    }
}


