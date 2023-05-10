package com.itsmobile.pokedex.model.location

import com.google.gson.reflect.TypeToken

class Locations : ArrayList<LocationsItem>(){

    fun getLocationFilteredByVersion(version: String) : ArrayList<LocationsItem>{
        val locationsFiltered = ArrayList<LocationsItem>()

        val versions = version.split("-")

        this.forEach{ location ->
            location.version_details.forEach {versionDetail ->
                if(versionDetail.version.name == versions[0]){
                    val version = ArrayList<VersionDetail>()
                    version.add(versionDetail)
                    locationsFiltered.add(LocationsItem(location.location_area, version))
                }
            }
        }

        return locationsFiltered
    }
}