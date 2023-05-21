package com.itsmobile.pokedex.model.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
    val locations = MutableLiveData<ArrayList<LocationsItem>>()
}