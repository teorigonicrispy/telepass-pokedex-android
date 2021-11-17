package it.crispybacon.telepass.dto

data class PokemonDTO(
    val id: Int?,
    val name: String?,
    val abilities: ArrayList<Ability>?,
    val moves: ArrayList<Move>?,
    val sprites: Sprites?,
    val stats: ArrayList<Stat>?,
    val types: ArrayList<TypeElement>?
) {
    fun toPokemon() {

    }
}

data class Ability(
    val ability: NamedAPIResourceDTO?,
    val isHidden: Boolean?,
    val slot: Int?
)

data class Move(
    val move: NamedAPIResourceDTO?
)

data class Sprites(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class Stat(
    val baseStat: Int?,
    val effort: Int?,
    val statval: NamedAPIResourceDTO?
)

data class TypeElement(
    val slot: Int?,
    val type: NamedAPIResourceDTO?
)
