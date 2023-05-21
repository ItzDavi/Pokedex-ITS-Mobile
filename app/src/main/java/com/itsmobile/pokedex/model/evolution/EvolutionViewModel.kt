package com.itsmobile.pokedex.model.evolution

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EvolutionViewModel : ViewModel() {
    val evolution = MutableLiveData<ArrayList<Map<String, String>>>()
}