package com.itsmobile.pokedex

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.itsmobile.pokedex.model.pokemon.PokemonEntry
import com.itsmobile.pokedex.model.pokemon.PokemonItem
import com.itsmobile.pokedex.model.pokemon.PokemonSpecies

class PokedexActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        window.statusBarColor = getColor(R.color.primary)

        val sharedPref = this.getSharedPreferences("version", Context.MODE_PRIVATE) // chiamo la shared pref "version"
        val version = sharedPref.getString("version", "") // prendo la stringa della shared

        val ver = findViewById<TextView>(R.id.ver)
        when(version){
            "https://pokeapi.co/api/v2/pokedex/2"-> ver.text = "I"
            "https://pokeapi.co/api/v2/pokedex/3" -> ver.text = "II"
            "https://pokeapi.co/api/v2/pokedex/4" -> ver.text = "III"
            "https://pokeapi.co/api/v2/pokedex/5" -> ver.text = "IV"
            "https://pokeapi.co/api/v2/pokedex/8" -> ver.text = "V"
            "https://pokeapi.co/api/v2/pokedex/12" -> ver.text = "VI"
            "https://pokeapi.co/api/v2/pokedex/16" -> ver.text = "VII"
        }

            // ver.text = version
            sendAPIRequest(version.toString())
        }
        private fun sendAPIRequest(gen:String) {//creazione metodo con API, RICORDA I PERMESSI
                val queque = Volley.newRequestQueue(this)
                val jsonReq = JsonObjectRequest(
                        Request.Method.GET,
                        gen,
                        null,
                        {response ->
                            val poke = Gson().fromJson(response.toString(), PokemonItem::class.java)
                            Log.d("poke",poke.toString())
                            //testo.text = abilities
                            val pokemonEntries = ArrayList<PokemonEntry>()
                            for (pokemonEntry in poke.pokemon_entries) {
                                pokemonEntries.add(PokemonEntry(pokemonEntry.entry_number, PokemonSpecies(pokemonEntry.pokemon_species.name,pokemonEntry.pokemon_species.url)))
                            }

                            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                            val pokemonAdapter = PokemonAdapter(pokemonEntries)

                            recyclerView.apply {
                                adapter = pokemonAdapter
                                layoutManager = LinearLayoutManager(this@PokedexActivity, LinearLayoutManager.VERTICAL, false)
                            }

                        },
                        { _ ->

                        }

                )
                queque.add(jsonReq)
        }


}