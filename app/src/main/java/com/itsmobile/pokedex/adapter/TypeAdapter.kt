package com.itsmobile.pokedex.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.Type
import com.itsmobile.pokedex.model.pokemon.TypeOutside

class TypeAdapter(var types: ArrayList<TypeOutside>): RecyclerView.Adapter<TypeAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_type, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }



    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val type = types[position].type

        holder.view.findViewById<TextView>(R.id.name).text = type.name
        holder.view.findViewById<ConstraintLayout>(R.id.container).setBackgroundColor(Color.parseColor(getColor(type.name.uppercase())))
    }

    override fun getItemCount(): Int = types.size

    private fun getColor(name: String): String {
        var color = "#FF0000";
        when (name){
            "POISON" -> color = "#784CD7"
            "GRASS" -> color = "#00FF00"
            "PSYCHIC" -> color = "#ff5498"
            "NORMAL" -> color = "#abab99"
            "FIRE" -> color = "#fe4522"
            "WATER" -> color = "#3399ff"
            "ELECTRIC" -> color = "#fecd33"
            "ICE" -> color = "#66cdfe"
            "FIGHTING" -> color = "#ba5545"
            "GROUND" -> color = "#dcbc54"
            "FLYING" -> color = "#8899ff"
            "BUG" -> color = "#abbb22"
            "ROCK" -> color = "#baaa67"
            "GHOST" -> color = "#6767bb"
            "DRAGON" -> color = "#7666ef"
            "DARK" -> color = "#775445"
            "STEEL" -> color = "#a8aaba"
            "FAIRY" -> color = "#ee98ef"
        }

        return color
    }
}