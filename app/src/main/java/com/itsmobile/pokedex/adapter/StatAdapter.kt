package com.itsmobile.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.Stat

class StatAdapter(private val stats: ArrayList<Stat>) : RecyclerView.Adapter<StatAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_stat, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val stat = stats[position]


        holder.view.findViewById<TextView>(R.id.name).text = stat.name
        holder.view.findViewById<TextView>(R.id.value).text = stat.value.toInt().toString()

        var maxStat = 300

        if(position == stats.size - 1){
            maxStat = 700
        }

        var widthBarOutside = holder.view.findViewById<ConstraintLayout>(R.id.barOutside).layoutParams.width

        holder.view.findViewById<View>(R.id.barInside).layoutParams.width = ((stat.value / maxStat) * widthBarOutside).toInt()

    }

    override fun getItemCount() = stats.size
}