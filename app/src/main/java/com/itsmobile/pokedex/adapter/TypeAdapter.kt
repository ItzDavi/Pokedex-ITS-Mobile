package com.itsmobile.pokedex.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.Type

class TypeAdapter(var types: ArrayList<Type>): RecyclerView.Adapter<TypeAdapter.CustomViewHolder>() {
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
        val type = types[position]

        holder.view.findViewById<TextView>(R.id.name).text = type.name
        holder.view.findViewById<ConstraintLayout>(R.id.container).setBackgroundColor(Color.parseColor(getColor(type.name)))
    }

    override fun getItemCount(): Int = types.size

    private fun getColor(name: String): String {
        var color = "#FF0000";
        when (name){
            "poison" -> color = "#784CD7"
            "grass" -> color = "#00FF00"
        }

        return color
    }
}