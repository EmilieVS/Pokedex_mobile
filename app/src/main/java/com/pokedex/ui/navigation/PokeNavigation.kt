package com.pokedex.ui.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pokedex.ui.screen.HomePage
import PokemonDetailScreen

@Composable
fun PokeNavigation () {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomePage(
                pokeID = { id ->
                    navController.navigate("details/$id")
                }
            )
        }
        composable("details/{id}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (pokemonId != null) {
                PokemonDetailScreen(pokemonId)
            }
        }
    }
}