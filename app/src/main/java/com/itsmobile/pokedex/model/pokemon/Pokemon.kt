package com.itsmobile.pokedex.model.pokemon

import com.itsmobile.pokedex.model.Ability
import com.itsmobile.pokedex.model.Stat
import com.itsmobile.pokedex.model.Type

class Pokemon(var base_experience: Int, var name: String, var stats: ArrayList<Stat>, val sprites: Sprite, val abilities: ArrayList<Ability>, val weight: Int, val height: Int, val types: ArrayList<TypeOutside>) {

}