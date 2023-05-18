package com.itsmobile.pokedex.model

class PokemonEntry(var name: String, var progressive: String/*, var image: URL*/) {

    fun getNameString(): String{
        return name
    }
    fun getVersionString(): String {
        return progressive
    }
    /*fun getNumberString(): URL {
        return  image
    }*/
}
