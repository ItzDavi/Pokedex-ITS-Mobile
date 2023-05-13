package com.itsmobile.pokedex.model.pokemon

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.fragment.PokemonDetailFragment
import com.itsmobile.pokedex.model.machines.Machine
import com.itsmobile.pokedex.model.machines.Machines

class Move(val name: String, val url: String) {
    var damage_class = ""
    var type = ""
    var howObtain = ""

    fun setMachine(context: Context, acquired: String, version: String){
        val queue = Volley.newRequestQueue(context)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                if(response.getJSONObject("damage_class").getString("name") == "physical"){
                    damage_class = "PHY"
                }else{
                    damage_class = response.getJSONObject("damage_class").getString("name")
                }
                type = response.getJSONObject("type").getString("name")

                if(acquired == "machine"){
                    val machines = Gson().fromJson(response.toString(), Machines::class.java)

                    for(machine in machines.machines){
                        if(machine.version_group.name == version){
                            val queue = Volley.newRequestQueue(context)

                            val jsonRequest = JsonObjectRequest(
                                Request.Method.GET,
                                machine.machine.url,
                                null,
                                { response ->
                                    howObtain = response.getJSONObject("item").getString("name")
                                },
                                { error ->
                                    Log.d("errore", error.message.toString())
                                }
                            )
                            queue.add(jsonRequest)
                        }
                    }
                }
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }
}