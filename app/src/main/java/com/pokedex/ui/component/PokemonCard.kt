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
        "grass" -> Color(0xFF39FF14)
        "fire" -> Color(0xFFFF073A)
        "water" -> Color(0xFF00F5FF)
        "electric" -> Color(0xFFFFF700)
        "psychic" -> Color(0xFFFF00FF)
        "ice" -> Color(0xFF00FFFF)
        "dragon" -> Color(0xFF7B00FF)
        "dark" -> Color(0xFF0A0A0A)
        "fairy" -> Color(0xFFFF66CC)
        "normal" -> Color(0xFFCCCCCC)
        "fighting" -> Color(0xFFFF3131)
        "poison" -> Color(0xFFB300FF)
        "ground" -> Color(0xFFFFAA00)
        "flying" -> Color(0xFF66CCFF)
        "bug" -> Color(0xFF76FF03)
        "rock" -> Color(0xFFFF8800)
        "ghost" -> Color(0xFF9400D3)
        "steel" -> Color(0xFF00E5FF)
        else -> Color(0xFFB0B0B0)
    }
}
