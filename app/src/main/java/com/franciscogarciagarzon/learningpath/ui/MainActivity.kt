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
import com.franciscogarciagarzon.learningpath.ui.screens.navigation.ScreenRouter
import com.franciscogarciagarzon.learningpath.ui.screens.pokemondetail.PokemonDetail
import com.franciscogarciagarzon.learningpath.ui.screens.pokemonlist.PokedexList
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme
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
                        navController = navController, startDestination = ScreenRouter.PokemonList.route
                    ) {
                        composable(ScreenRouter.PokemonList.route) {
                            PokedexList(showPokemonDetail = { pokemonName ->
                                Log.d("MainActivity", "navigation lambda showPokemonDetail ($pokemonName)")
                                navController.navigate(
                                    route = ScreenRouter.PokemonDetail.createRoute(pokemonName)
                                )
                            }, favNavigation = { navController.navigate(route = ScreenRouter.PokemonDetail.createRoute("ogerpon-cornerstone-mask")) })
                        }
                        composable(route = ScreenRouter.PokemonDetail.route) { backStackEntry ->
                            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                            requireNotNull(pokemonName)
                            PokemonDetail(id = pokemonName, navigateUp = {
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


