package com.itsmobile.pokedex.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.pokemon.MoveVersion
import kotlin.collections.ArrayList

class MoveAdapter (private val moves: ArrayList<MoveVersion>, private val howObtain: String) : RecyclerView.Adapter<MoveAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_move, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
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