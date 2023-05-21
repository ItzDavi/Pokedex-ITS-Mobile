package com.itsmobile.pokedex.model

class Version ( var name: String, var number: String, var url: String ) {
    fun getNameVersion(): String{
        return name
    }
    fun getNumVersion(): String{
        return number
    }
}