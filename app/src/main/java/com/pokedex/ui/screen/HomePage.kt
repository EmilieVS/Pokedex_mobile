package com.pokedex.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
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
    ) {
        // --- Header ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Red, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pokedex",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

        // getting the pokemon list
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // ✅ 2 par ligne
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(pokemonList) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    compact = false,
                    onClick = { pokeID(pokemon.id) }
                )
            }
        }
    }
}



//@Composable
//fun PokemonCard(
//    pokemon: Pokemon,
//    onClick: () -> Unit
//) {
//
//    Box(
//        modifier = Modifier
//            .shadow(
//                elevation = 12.dp,
//                shape = RoundedCornerShape(20.dp),
//                clip = false
//            )
//            .background(
//                color = getTypeColor(pokemon.type.firstOrNull() ?: "normal"),
//                shape = RoundedCornerShape(20.dp)
//            )
//            .padding(2.dp) // glow
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(0.7f) // to get the rounded shape
//                .clickable { onClick() },
//            shape = RoundedCornerShape(20.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = Color(0xFF2D2D2D)
//            ),
//            border = BorderStroke(
//                1.dp,
//                Color(0xFF404040)
//            )
//        ){
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(12.dp)
//            ) {
//                // pokemon number and type
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "#${pokemon.num}",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//
//                    // getting color depending on type
//                    Box(
//                        modifier = Modifier
//                            .size(12.dp)
//                            .background(
//                                getTypeColor(pokemon.type.firstOrNull() ?: "Normal"),
//                                shape = CircleShape
//                            )
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Image with circular shaped background
//                Box(
//                    modifier = Modifier
//                        .size(120.dp)
//                        .background(
//                            Color(0xFF404040),
//                            shape = CircleShape
//                        )
//                        .align(Alignment.CenterHorizontally),
//                    contentAlignment = Alignment.Center
//                ) {
//
//                    val context = LocalContext.current
//                    val resId = remember(pokemon.img) {
//                        context.resources.getIdentifier(
//                            pokemon.img,
//                            "drawable",
//                            context.packageName
//                        )
//                    }
//
//                    if (resId != 0) {
//                        Image(
//                            painter = painterResource(id = resId),
//                            contentDescription = pokemon.name,
//                            modifier = Modifier.size(80.dp),
//                            contentScale = ContentScale.Fit
//                        )
//                    }
//
//                }
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // Pokemon's name
//                Text(
//                    text = pokemon.name,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Information: Weight et Height
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Column {
//                        Text(
//                            text = "Weight",
//                            fontSize = 10.sp,
//                            color = Color.Gray
//                        )
//                        Text(
//                            text = pokemon.weight,
//                            fontSize = 12.sp,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//
//                    Column {
//                        Text(
//                            text = "Height",
//                            fontSize = 10.sp,
//                            color = Color.Gray
//                        )
//                        Text(
//                            text = pokemon.height,
//                            fontSize = 12.sp,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Start
//                ) {
//                    Text(
//                        text = "Type: ",
//                        fontSize = 10.sp,
//                        color = Color.Gray
//                    )
//                    Text(
//                        text = pokemon.type.joinToString(" / "),
//                        fontSize = 10.sp,
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun getTypeColor(type: String): Color {
//    return when (type.lowercase()) {
//        "grass" -> Color(0xFF4CAF50)
//        "fire" -> Color(0xFFFF5722)
//        "water" -> Color(0xFF2196F3)
//        "electric" -> Color(0xFFFFC107)
//        "psychic" -> Color(0xFFE91E63)
//        "ice" -> Color(0xFF00BCD4)
//        "dragon" -> Color(0xFF673AB7)
//        "dark" -> Color(0xFF424242)
//        "fairy" -> Color(0xFFFF4081)
//        "normal" -> Color(0xFF9E9E9E)
//        "fighting" -> Color(0xFFD32F2F)
//        "poison" -> Color(0xFF9C27B0)
//        "ground" -> Color(0xFF795548)
//        "flying" -> Color(0xFF03A9F4)
//        "bug" -> Color(0xFF4CAF50)
//        "rock" -> Color(0xFF795548)
//        "ghost" -> Color(0xFF9C27B0)
//        "steel" -> Color(0xFF607D8B)
//        else -> Color(0xFF757575)
//    }
//}






