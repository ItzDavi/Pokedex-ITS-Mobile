package com.itsmobile.pokedex

import com.itsmobile.pokedex.model.Type
import com.itsmobile.pokedex.model.pokemon.TypeOutside

abstract class TypeCalculator {
    companion object{
        enum class PokemonType {
            NORMAL,
            FIRE,
            WATER,
            ELECTRIC,
            GRASS,
            ICE,
            FIGHTING,
            POISON,
            GROUND,
            FLYING,
            PSYCHIC,
            BUG,
            ROCK,
            GHOST,
            DRAGON,
            DARK,
            STEEL,
            FAIRY
        }

        val typeWeakness = mapOf(
            PokemonType.NORMAL to listOf<PokemonType>(PokemonType.FIGHTING),
            PokemonType.FIRE to listOf(PokemonType.WATER, PokemonType.ROCK, PokemonType.GROUND),
            PokemonType.WATER to listOf(PokemonType.ELECTRIC, PokemonType.GRASS),
            PokemonType.ELECTRIC to listOf(PokemonType.GROUND),
            PokemonType.GRASS to listOf(PokemonType.FIRE, PokemonType.ICE, PokemonType.POISON, PokemonType.FLYING, PokemonType.BUG),
            PokemonType.ICE to listOf(PokemonType.FIRE, PokemonType.FIGHTING, PokemonType.ROCK, PokemonType.STEEL),
            PokemonType.FIGHTING to listOf(PokemonType.PSYCHIC, PokemonType.FLYING, PokemonType.FAIRY),
            PokemonType.POISON to listOf(PokemonType.GROUND, PokemonType.PSYCHIC),
            PokemonType.GROUND to listOf(PokemonType.WATER, PokemonType.GRASS, PokemonType.ICE),
            PokemonType.FLYING to listOf(PokemonType.ELECTRIC, PokemonType.ICE, PokemonType.ROCK),
            PokemonType.PSYCHIC to listOf(PokemonType.BUG, PokemonType.GHOST, PokemonType.DARK),
            PokemonType.BUG to listOf(PokemonType.FIRE, PokemonType.ROCK, PokemonType.FLYING),
            PokemonType.ROCK to listOf(PokemonType.WATER, PokemonType.GRASS, PokemonType.FIGHTING, PokemonType.GROUND, PokemonType.STEEL),
            PokemonType.GHOST to listOf(PokemonType.GHOST, PokemonType.DARK),
            PokemonType.DRAGON to listOf(PokemonType.ICE, PokemonType.DRAGON, PokemonType.FAIRY),
            PokemonType.DARK to listOf(PokemonType.FIGHTING, PokemonType.BUG, PokemonType.FAIRY),
            PokemonType.STEEL to listOf(PokemonType.FIRE, PokemonType.FIGHTING, PokemonType.DRAGON),
            PokemonType.FAIRY to listOf(PokemonType.POISON, PokemonType.STEEL)
        )

        val typeResistances = mapOf(
            PokemonType.NORMAL to listOf<PokemonType>(PokemonType.GHOST),
            PokemonType.FIRE to listOf(PokemonType.FIRE, PokemonType.GRASS, PokemonType.BUG, PokemonType.ICE, PokemonType.STEEL, PokemonType.FAIRY),
            PokemonType.WATER to listOf(PokemonType.FIRE, PokemonType.WATER, PokemonType.ICE, PokemonType.STEEL),
            PokemonType.ELECTRIC to listOf(PokemonType.ELECTRIC, PokemonType.FLYING, PokemonType.STEEL),
            PokemonType.GRASS to listOf(PokemonType.WATER, PokemonType.GRASS, PokemonType.ELECTRIC, PokemonType.GROUND),
            PokemonType.ICE to listOf(PokemonType.ICE),
            PokemonType.FIGHTING to listOf(PokemonType.ROCK, PokemonType.BUG, PokemonType.DARK),
            PokemonType.POISON to listOf(PokemonType.GRASS, PokemonType.FIGHTING, PokemonType.POISON, PokemonType.BUG, PokemonType.FAIRY),
            PokemonType.GROUND to listOf(PokemonType.ELECTRIC, PokemonType.ROCK, PokemonType.POISON),
            PokemonType.FLYING to listOf(PokemonType.GROUND, PokemonType.GRASS, PokemonType.FIGHTING, PokemonType.BUG),
            PokemonType.PSYCHIC to listOf(PokemonType.PSYCHIC, PokemonType.FIGHTING),
            PokemonType.BUG to listOf(PokemonType.GRASS, PokemonType.FIGHTING, PokemonType.GROUND),
            PokemonType.ROCK to listOf(PokemonType.FIRE, PokemonType.NORMAL, PokemonType.POISON, PokemonType.FLYING),
            PokemonType.GHOST to listOf(PokemonType.NORMAL, PokemonType.FIGHTING, PokemonType.POISON, PokemonType.BUG),
            PokemonType.DRAGON to listOf(PokemonType.FIRE, PokemonType.WATER, PokemonType.GRASS, PokemonType.ELECTRIC),
            PokemonType.DARK to listOf(PokemonType.PSYCHIC, PokemonType.GHOST, PokemonType.DARK),
            PokemonType.STEEL to listOf(PokemonType.POISON, PokemonType.GRASS, PokemonType.NORMAL, PokemonType.ROCK, PokemonType.PSYCHIC, PokemonType.DRAGON, PokemonType.BUG, PokemonType.FLYING, PokemonType.ICE, PokemonType.STEEL, PokemonType.FAIRY),
            PokemonType.FAIRY to listOf(PokemonType.DRAGON, PokemonType.FIGHTING, PokemonType.BUG, PokemonType.DARK)
        )

        fun calculateWeaknessesAndResistances(type: String, type2: String): Pair<ArrayList<TypeOutside>, ArrayList<TypeOutside>> {
            val weaknesses = mutableListOf<PokemonType>()
            val resistances = mutableListOf<PokemonType>()

            var type = PokemonType.valueOf(type.uppercase())
            var type2 = PokemonType.valueOf(type2.uppercase())

            // calculate weakness from type1 and type2
            typeWeakness[type]?.forEach {type1Value ->
                if(typeWeakness[type2]?.contains(type1Value) == true){
                    weaknesses.add(type1Value)
                }else if(typeResistances[type2]?.contains(type1Value) == false){
                    weaknesses.add(type1Value)
                }
            }

            typeWeakness[type2]?.forEach {type2 ->
                if(typeWeakness[type]?.contains(type2) == true && !weaknesses.contains(type2)){
                    weaknesses.add(type2)
                }else if(typeResistances[type]?.contains(type2) == false && !weaknesses.contains(type2)){
                    weaknesses.add(type2)
                }
            }

            // calculate resistances from type1 and type2
            typeResistances[type]?.forEach {type1Value ->
                if(typeResistances[type2]?.contains(type1Value) == true){
                    resistances.add(type1Value)
                }else if(typeWeakness[type2]?.contains(type1Value) == false){
                    resistances.add(type1Value)
                }
            }

            typeResistances[type2]?.forEach {type2 ->
                if(typeResistances[type]?.contains(type2) == true && !resistances.contains(type2)){
                    resistances.add(type2)
                }else if(typeWeakness[type]?.contains(type2) == false && !resistances.contains(type2)){
                    resistances.add(type2)
                }
            }


            // Specific cases of resistances

            if(type == PokemonType.FLYING || type2 == PokemonType.FLYING){
                resistances.add(PokemonType.GROUND)
            }

            if(type == PokemonType.GHOST || type2 == PokemonType.GHOST){
                resistances.add(PokemonType.NORMAL)
                resistances.add(PokemonType.FIGHTING)
            }

            if(type == PokemonType.FAIRY || type2 == PokemonType.FAIRY){
                resistances.add(PokemonType.DRAGON)
            }

            if(type == PokemonType.GROUND || type2 == PokemonType.GROUND){
                resistances.add(PokemonType.FLYING)
            }

            if(type == PokemonType.DARK || type2 == PokemonType.DARK){
                resistances.add(PokemonType.PSYCHIC)
            }

            if(type == PokemonType.NORMAL || type2 == PokemonType.NORMAL){
                resistances.add(PokemonType.GHOST)
            }

            if(type == PokemonType.STEEL || type2 == PokemonType.STEEL){
                resistances.add(PokemonType.POISON)
            }


            var newWeaknesses = ArrayList<TypeOutside>()

            weaknesses.forEach { weak ->
                newWeaknesses.add(TypeOutside(Type(weak.name)))
            }

            var newResistances = ArrayList<TypeOutside>()

            resistances.forEach { resistance ->
                newResistances.add(TypeOutside(Type(resistance.name)))
            }

            return Pair(newWeaknesses, newResistances)
        }

        fun convertPokemonTypeToTypeOutside(type: String) : Pair<ArrayList<TypeOutside>, ArrayList<TypeOutside>>{
            var resistances = ArrayList<TypeOutside>()
            var weaknesses = ArrayList<TypeOutside>()

            typeResistances[PokemonType.valueOf(type.uppercase())]?.forEach {
                resistances.add(TypeOutside(Type(it.name)))
            }

            typeWeakness[PokemonType.valueOf(type.uppercase())]?.forEach {
                weaknesses.add(TypeOutside(Type(it.name)))
            }

            return Pair(weaknesses, resistances)
        }

    }
}