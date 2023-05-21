package com.itsmobile.pokedex.model.evolution

data class Chain(
    val evolution_details: List<Any>,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: SpeciesX
)