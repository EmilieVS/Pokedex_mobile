package com.pokedex.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.data.model.Pokemon
import com.pokedex.data.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PokeRepository) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    fun loadPokemon() {
        viewModelScope.launch {
            val pokemon = repository.loadPokemonData()
            _pokemonList.value = pokemon
        }
    }
}