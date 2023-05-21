package com.itsmobile.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.location.Locations
import com.itsmobile.pokedex.model.location.LocationsItem
import java.util.*
import kotlin.collections.ArrayList

class LocationAdapter(val locations: ArrayList<LocationsItem>): RecyclerView.Adapter<LocationAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_location, parent, false) as ViewGroup
        return LocationAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationAdapter.CustomViewHolder, position: Int) {
        val location = locations[position]
        holder.view.findViewById<TextView>(R.id.route).text = location.location_area.name
        holder.view.findViewById<RecyclerView>(R.id.encounterRecycler).apply {
            adapter = EncounterAdapter(location.version_details[0].encounter_details)
            layoutManager = LinearLayoutManager(holder.view.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun getItemCount(): Int = locations.size
}