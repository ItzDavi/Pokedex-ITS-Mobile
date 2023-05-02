package com.itsmobile.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.Ability

class AbilityAdapter (var abilities: ArrayList<Ability>): RecyclerView.Adapter<TypeAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_ability, parent, false) as ViewGroup
        return TypeAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeAdapter.CustomViewHolder, position: Int) {
        val ability = abilities[position]

        holder.view.findViewById<TextView>(R.id.title).text = ability.title
    }

    override fun getItemCount(): Int = abilities.size
}