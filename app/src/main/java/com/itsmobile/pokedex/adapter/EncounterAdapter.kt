package com.itsmobile.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.location.EncounterDetail

class EncounterAdapter (val encounters: List<EncounterDetail>): RecyclerView.Adapter<EncounterAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EncounterAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_encounter, parent, false) as ViewGroup
        return EncounterAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: EncounterAdapter.CustomViewHolder, position: Int) {
        val encounter = encounters[position]
        holder.view.findViewById<TextView>(R.id.method).text = encounter.method.name
        holder.view.findViewById<TextView>(R.id.rateStat).text = "${encounter.chance}% - Lv. ${encounter.min_level}~${encounter.max_level}"
    }

    override fun getItemCount(): Int = encounters.size
}