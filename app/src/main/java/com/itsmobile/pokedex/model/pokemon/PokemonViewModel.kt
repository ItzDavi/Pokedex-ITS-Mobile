package com.itsmobile.pokedex.model.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel(){
    val pokemon = MutableLiveData<Pokemon>()
}