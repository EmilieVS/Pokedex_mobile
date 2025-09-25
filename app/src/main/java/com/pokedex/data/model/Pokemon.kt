package com.pokedex.data.model
import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val num: String,
    val name: String,
    val img: String,
    val type: List<String>,
    val height: String,
    val weight: String,
    val candy: String?,
    @SerializedName("candy_count")
    val candyCount: Int?, // Nullable
    val egg: String?,
    @SerializedName("spawn_chance")
    val spawnChance: Double?,
    @SerializedName("avg_spawns")
    val avgSpawns: Double?,
    @SerializedName("spawn_time")
    val spawnTime: String?,
    val multipliers: List<Double>?,
    val weaknesses: List<String>?,
    @SerializedName("next_evolution")
    val nextEvolution: List<Evolution>?,
    @SerializedName("prev_evolution")
    val prevEvolution: List<Evolution>?
)

data class Evolution(
    val num: String,
    val name: String
)

data class PokemonResponse(
    val pokemon: List<Pokemon>
)
