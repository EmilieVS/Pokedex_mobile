package com.pokedex.ui.screen

import com.pokedex.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pokedex.data.repository.PokeRepository
import com.pokedex.ui.component.PokemonCard


@Composable
fun HomePage(
    pokeID: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    val repository = remember { PokeRepository(context) }
    val viewModel = remember { HomeViewModel(repository) }

    val pokemonList by viewModel.pokemonList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPokemon()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        // --- Header ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .wrapContentHeight()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//                painter = rememberAsyncImagePainter(model = R.drawable.pokemon_logo),
//                contentDescription = "Logo SVG",
//                modifier = Modifier
//                    .size(80.dp)
//            )
            Text(
                text = "Pokémon",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700),
                textAlign = TextAlign.Center
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.pokeball_logo),
                    contentDescription = "Logo SVG",
                    modifier = Modifier
                        .size(64.dp)
                    )
                Text(
                    text = "Pokedex",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        // getting the pokemon list
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 per row
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(pokemonList) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    compact = false,
                    showDetails = false,
                    onClick = { pokeID(pokemon.id) }
                )
            }
        }
    }
}







