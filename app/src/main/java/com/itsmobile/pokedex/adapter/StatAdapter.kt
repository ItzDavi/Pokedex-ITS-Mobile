package com.itsmobile.pokedex.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.Stat

class StatAdapter(private val stats: ArrayList<Stat>) : RecyclerView.Adapter<StatAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_stat, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val stat = stats[position]


        when(stat.stat.name){
            "hp" -> holder.view.findViewById<TextView>(R.id.name).text = "HP"
            "attack" -> holder.view.findViewById<TextView>(R.id.name).text = "ATK"
            "defense" -> holder.view.findViewById<TextView>(R.id.name).text = "DEF"
            "special-attack" -> holder.view.findViewById<TextView>(R.id.name).text = "SATK"
            "special-defense" -> holder.view.findViewById<TextView>(R.id.name).text = "SDEF"
            "speed" -> holder.view.findViewById<TextView>(R.id.name).text = "SPD"
            "tot" -> holder.view.findViewById<TextView>(R.id.name).text = "TOT"
        }

        holder.view.findViewById<TextView>(R.id.value).text = stat.base_stat.toInt().toString()

        var maxStat = 150

        if(position == stats.size - 1){
            maxStat = 700
        }

        val widthBarOutside = holder.view.findViewById<ConstraintLayout>(R.id.barOutside).layoutParams.width
        val widthBarInside = ((stat.base_stat / maxStat) * widthBarOutside).toInt() * 3
        holder.view.findViewById<View>(R.id.barInside).layoutParams.width = widthBarInside

    }

    override fun getItemCount() = stats.size
}