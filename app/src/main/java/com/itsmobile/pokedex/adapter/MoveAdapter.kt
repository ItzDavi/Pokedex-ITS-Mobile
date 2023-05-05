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

        val queue = Volley.newRequestQueue(holder.view.context)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            move.move.url,
            null,
            { response ->

                if(response.getJSONObject("damage_class").getString("name") == "physical"){
                    holder.view.findViewById<TextView>(R.id.effect).text = "PHY"
                }else{
                    holder.view.findViewById<TextView>(R.id.effect).text = response.getJSONObject("damage_class").getString("name")
                }

                holder.view.findViewById<TextView>(R.id.type).text = response.getJSONObject("type").getString("name")

                when(howObtain){
                    "level-up" -> {
                        holder.view.findViewById<TextView>(R.id.level).text = "Lv. ${move.version_group_details[0].level_learned_at}"
                    }

                    "machine" -> {

                        val machines = Gson().fromJson(response.toString(), Machines::class.java)

                        for(machine in machines.machines){
                            if(machine.version_group.name == "red-blue"){
                                setMTName(machine.machine.url, holder.view.context, holder.view.findViewById(R.id.level))
                            }
                        }

                    }

                    "egg" -> holder.view.findViewById<TextView>(R.id.level).text = "Lv. 1"

                }
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }

    private fun setMTName(url: String, context: Context, howObtain: TextView){
        val queue = Volley.newRequestQueue(context)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                howObtain.text = response.getJSONObject("item").getString("name")
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }

    override fun getItemCount(): Int = moves.size
}