package com.pokedex.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.pokedex.data.model.Pokemon
import com.pokedex.data.model.PokemonResponse

class PokeRepository(private val context: Context) {

    fun loadPokemonData(): List<Pokemon> {
        return try {
            val jsonString = context.assets.open("pokemon.json")
                .bufferedReader()
                .use { it.readText() }

            val gson = Gson()
            val pokemonResponse = gson.fromJson(jsonString, PokemonResponse::class.java)
            pokemonResponse.pokemon
        } catch (e: Exception) {
            Log.e("Pokemon", "Error parsing JSON", e)
            emptyList()
        }
    }
}



