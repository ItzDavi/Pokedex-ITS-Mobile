package com.itsmobile.pokedex.model.location

class Locations : ArrayList<LocationsItem>(){
    fun getLocationFilteredByVersion() : ArrayList<LocationsItem>{
        val locationsFiltered = ArrayList<LocationsItem>()

        this.forEach{ location ->
            location.version_details.forEach {versionDetail ->
                if(versionDetail.version.name == "red-blue"){
                    val version = ArrayList<VersionDetail>()
                    version.add(versionDetail)
                    locationsFiltered.add(LocationsItem(location.location_area, version))
                }
            }
        }

        return locationsFiltered
    }
}