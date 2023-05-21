package com.itsmobile.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.location.LocationsItem
import kotlin.collections.ArrayList

class LocationAdapter(private val locations: ArrayList<LocationsItem>): RecyclerView.Adapter<LocationAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_location, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val location = locations[position]
        holder.view.findViewById<TextView>(R.id.route).text = location.location_area.name
        holder.view.findViewById<RecyclerView>(R.id.encounterRecycler).apply {
            adapter = EncounterAdapter(location.version_details[0].encounter_details)
            layoutManager = LinearLayoutManager(holder.view.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun getItemCount(): Int = locations.size
}