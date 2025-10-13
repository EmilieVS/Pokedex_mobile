package com.pokedex.ui.component


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.data.model.Pokemon
import EvolutionInfo
import PokeInfo


@Composable
fun PokemonCard(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
    showDetails: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = getTypeColor(pokemon.type.firstOrNull() ?: "normal"),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(2.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
                .then(
                    if (onClick != null) Modifier.clickable { onClick() }
                    else Modifier
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D)),
            border = BorderStroke(1.dp, Color(0xFF404040))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#${pokemon.num}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                getTypeColor(pokemon.type.firstOrNull() ?: "Normal"),
                                shape = CircleShape
                            )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // --- Image ---
                val context = LocalContext.current
                val resId = remember(pokemon.img) {
                    context.resources.getIdentifier(
                        pokemon.img,
                        "drawable",
                        context.packageName
                    )
                }

                if (resId != 0) {
                    Box(
                        modifier = Modifier
                            .size(if (compact) 80.dp else 120.dp)
                            .background(Color(0xFF404040), shape = CircleShape)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = pokemon.name,
                            modifier = Modifier.size(if (compact) 60.dp else 90.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = pokemon.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                if (!compact) {
                    Spacer(modifier = Modifier.height(8.dp))

                    // --- Infos ---
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Weight", fontSize = 10.sp, color = Color.Gray)
                            Text(pokemon.weight, fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        Column {
                            Text("Height", fontSize = 10.sp, color = Color.Gray)
                            Text(pokemon.height, fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("Type: ", fontSize = 10.sp, color = Color.Gray)
                        Text(
                            text = pokemon.type.joinToString(" / "),
                            fontSize = 10.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (showDetails) {
                        Spacer(modifier = Modifier.height(8.dp))
                        PokeInfo("Height", pokemon.height)
                        PokeInfo("Weight", pokemon.weight)
                        PokeInfo("Weaknesses", pokemon.weaknesses?.joinToString(", ") ?: "Aucune")

                        pokemon.prevEvolution?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Previous Evolutions :", color = Color.White, fontWeight = FontWeight.Bold)
                            EvolutionInfo(it)
                        }

                        pokemon.nextEvolution?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Next Evolutions :", color = Color.White, fontWeight = FontWeight.Bold)
                            EvolutionInfo(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "grass" -> Color(0xFF4CAF50)
        "fire" -> Color(0xFFFF5722)
        "water" -> Color(0xFF00BCD4)
        "electric" -> Color(0xFFFFC107)
        "psychic" -> Color(0xFFE91E63)
        "ice" -> Color(0xFF2196F3)
        "dragon" -> Color(0xFF673AB7)
        "dark" -> Color(0xFF424242)
        "fairy" -> Color(0xFFFF4081)
        "normal" -> Color(0xFF9E9E9E)
        "fighting" -> Color(0xFFD32F2F)
        "poison" -> Color(0xFF9C27B0)
        "ground" -> Color(0xFF795548)
        "flying" -> Color(0xFF03A9F4)
        "bug" -> Color(0xFF4CAF50)
        "rock" -> Color(0xFF795548)
        "ghost" -> Color(0xFF9C27B0)
        "steel" -> Color(0xFF607D8B)
        else -> Color(0xFF757575)
    }
}
