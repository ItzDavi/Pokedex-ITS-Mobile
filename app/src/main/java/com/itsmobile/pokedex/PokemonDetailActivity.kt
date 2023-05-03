package com.itsmobile.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.itsmobile.pokedex.adapter.AbilityAdapter
import com.itsmobile.pokedex.adapter.StatAdapter
import com.itsmobile.pokedex.adapter.TypeAdapter
import com.itsmobile.pokedex.databinding.ActivityPokemonDetailBinding
import com.itsmobile.pokedex.model.Stat
import com.itsmobile.pokedex.model.pokemon.Pokemon
import com.itsmobile.pokedex.model.pokemon.PokemonViewModel
import com.itsmobile.pokedex.model.pokemon.StatInside

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPokemonSpecies("https://pokeapi.co/api/v2/pokemon-species/151")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentView, PokemonDetailFragment.newInstance("https://pokeapi.co/api/v2/pokemon-species/151"))
            .commit()

        binding.location.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonDetailFragment.newInstance("https://pokeapi.co/api/v2/pokemon-species/151"))
                .commit()
        }

        binding.location.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonLocationFragment.newInstance(""))
                .commit()
        }
    }

    fun getPokemonSpecies(url: String){
        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                // resultText.text = response.get("name").toString()
                val urlPokemon = response.getJSONArray("varieties").getJSONObject(0).getJSONObject("pokemon").getString("url").toString()
                getPokemonDetail(urlPokemon)
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        // val pokemon = Gson().fromJson<Pokemon>(response.toString(), Pokemon::class.java)
        queue.add(jsonRequest)
    }

    private fun getPokemonDetail(url: String){
        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                // resultText.text = response.get("name").toString()
                // binding.pokemonNameText.text = response.getString("name")
                val pokemon = Gson().fromJson(response.toString(), Pokemon::class.java)
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }
}