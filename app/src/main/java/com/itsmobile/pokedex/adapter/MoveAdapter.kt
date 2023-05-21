package com.itsmobile.pokedex.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.machines.Machines
import com.itsmobile.pokedex.model.pokemon.MoveVersion
import kotlin.collections.ArrayList

class MoveAdapter (val moves: ArrayList<MoveVersion>, val howObtain: String) : RecyclerView.Adapter<MoveAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoveAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_move, parent, false) as ViewGroup
        return MoveAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoveAdapter.CustomViewHolder, position: Int) {
        val move = moves[position]

        holder.view.findViewById<TextView>(R.id.name).text = move.move.name

        holder.view.findViewById<TextView>(R.id.type).text = move.move.type
        holder.view.findViewById<TextView>(R.id.effect).text = move.move.damage_class

        when(howObtain){
            "level-up" -> holder.view.findViewById<TextView>(R.id.level).text = "Lv. ${move.version_group_details[0].level_learned_at}"
            "machine" -> holder.view.findViewById<TextView>(R.id.level).text = move.move.howObtain
            "eggs" -> holder.view.findViewById<TextView>(R.id.level).text = "Lv. 1"
        }
    }

    override fun getItemCount(): Int = moves.size
}