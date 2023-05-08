package com.itsmobile.pokedex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.ability.EffectEntries
import com.itsmobile.pokedex.model.evolution.Chain
import java.util.*
import kotlin.collections.ArrayList

class EvolutionAdapter (val evolutions: ArrayList<Map<String, String>>) : RecyclerView.Adapter<EvolutionAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EvolutionAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_evolution, parent, false) as ViewGroup
        return EvolutionAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: EvolutionAdapter.CustomViewHolder, position: Int) {
        val evolution = evolutions[position]

        if (position < evolutions.size - 1){
            holder.view.findViewById<TextView>(R.id.level).text = evolutions[position + 1]["lv"]
            val preEvo = holder.view.findViewById<ImageView>(R.id.preEvolution)
            val postEvo = holder.view.findViewById<ImageView>(R.id.postEvolution)
            Glide.with(holder.view.context).load(evolution["url"]).into(preEvo)
            Glide.with(holder.view.context).load(evolutions[position + 1]["url"]).into(postEvo)
        }
    }


    override fun getItemCount(): Int = evolutions.size - 1
}