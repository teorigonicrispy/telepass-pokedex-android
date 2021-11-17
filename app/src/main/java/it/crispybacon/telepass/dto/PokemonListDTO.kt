package it.crispybacon.telepass.dto

data class PokemonListDTO(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: ArrayList<NamedAPIResourceDTO>?
)