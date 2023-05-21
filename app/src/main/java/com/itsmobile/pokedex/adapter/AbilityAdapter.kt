package com.itsmobile.pokedex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.model.Ability
import com.itsmobile.pokedex.model.ability.EffectEntries
import java.util.*
import kotlin.collections.ArrayList

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

        holder.view.findViewById<TextView>(R.id.title).text = ability.ability.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        val queue = Volley.newRequestQueue(holder.view.context)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            ability.ability.url,
            null,
            { response ->
                val ab = Gson().fromJson(response.toString(), EffectEntries::class.java)
                for (effect in ab.effect_entries){
                    if(effect.language.name == "en"){
                        holder.view.findViewById<TextView>(R.id.description).text = effect.effect
                    }
                }

            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }

    override fun getItemCount(): Int = abilities.size
}