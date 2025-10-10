package com.pokedex.ui.component

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetails(
    val id: Long,
    val num:Int,
    val name : String,
    val img : String,
    val type: List<String>,
    val height : String,
    val weight : String,
    val candy : String,
    val weaknesses : List<String>,
    val next_evolution: List<Evolution>? = null,
    val prev_evolution: List<Evolution>? = null
) : Parcelable

@Parcelize
data class Evolution(
    val num: String,
    val name: String
) : Parcelable