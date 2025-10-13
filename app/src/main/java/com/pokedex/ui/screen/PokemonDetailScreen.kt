import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.data.model.Evolution
import com.pokedex.data.repository.PokeRepository
import com.pokedex.ui.component.PokemonCard
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource

@Composable
fun PokemonDetailScreen(
    pokemonId: Int,

) {
  val context = LocalContext.current
    val repository = remember { PokeRepository(context) }
    val pokemon = remember { repository.getPokemonById(pokemonId) }

    if (pokemon == null) { // if null for api future needs
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Pokemon not found", color = Color.White)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonCard(
            pokemon = pokemon,
            compact = false,
            showDetails = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PokeInfo(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.End)
    }
}

@Composable
fun EvolutionInfo(evolutions: List<Evolution>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(evolutions) { evo ->
            EvolutionCard(evo)
        }
    }
}

@Composable
fun EvolutionCard(evo: Evolution) {
    val context = LocalContext.current
    val resId = remember(evo.num) {
        context.resources.getIdentifier("pokemon_${evo.num}", "drawable", context.packageName)
    }

    Column(
        modifier = Modifier.width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (resId != 0) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = evo.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = evo.name,
            color = Color.White,
            fontSize = 12.sp,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}