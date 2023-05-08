package com.itsmobile.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.itsmobile.pokedex.databinding.ActivityPokemonDetailBinding
import com.itsmobile.pokedex.fragment.*
import com.itsmobile.pokedex.model.evolution.Evolution
import com.itsmobile.pokedex.model.evolution.EvolutionViewModel
import com.itsmobile.pokedex.model.evolution.EvolvesTo
import com.itsmobile.pokedex.model.location.LocationViewModel
import com.itsmobile.pokedex.model.location.Locations
import com.itsmobile.pokedex.model.pokemon.Pokemon
import com.itsmobile.pokedex.model.pokemon.PokemonViewModel

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailBinding
    val viewModel : PokemonViewModel by viewModels()
    val locationsViewModel : LocationViewModel by viewModels()
    val evolutionViewModel : EvolutionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentView, LoadingFragment.newInstance())
            .commit()

        getPokemonSpecies("https://pokeapi.co/api/v2/pokemon-species/abra")

        binding.info.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonDetailFragment.newInstance())
                .commit()
        }

        binding.evolution.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonEvolutionFragment.newInstance())
                .commit()
        }

        binding.location.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonLocationFragment.newInstance())
                .commit()
        }

        binding.moves.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonMovesFragment.newInstance())
                .commit()
        }
    }

    private fun getPokemonSpecies(url: String){
        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val urlPokemon = response.getJSONArray("varieties").getJSONObject(0).getJSONObject("pokemon").getString("url").toString()
                getPokemonDetail(urlPokemon)
                getEvolution(response.getJSONObject("evolution_chain").getString("url"))
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }

    private fun getPokemonDetail(url: String){
        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val pokemon = Gson().fromJson(response.toString(), Pokemon::class.java)
                viewModel.pokemon.value = pokemon
                // getLocation(response.getString("location_area_encounters"))
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentView, PokemonDetailFragment.newInstance())
                    .commit()
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }


    private fun getLocation(url: String){
        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                var locations = Gson().fromJson(response.toString(), Locations::class.java)

                locationsViewModel.locations.value = locations.getLocationFilteredByVersion()
            },
            { error ->
                Log.e("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }

    private fun getEvolution(url: String){
        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val evolutions = Gson().fromJson(response.toString(), Evolution::class.java)

                var evo = ArrayList<Map<String, String>>()
                var regex = Regex("""\d+(?=/?$)""").find(evolutions.chain.species.url)?.value.toString()
                evo.add(mapOf("url" to "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${regex}.png"))

                evo = getEvolutionsRecursive(evolutions.chain.evolves_to, evo)

                evolutionViewModel.evolution.value = evo
            },
            { error ->
                Log.d("errore", error.message.toString())
            }
        )
        queue.add(jsonRequest)
    }

    private fun getEvolutionsRecursive(evolution: List<EvolvesTo>, evo: ArrayList<Map<String, String>>) : ArrayList<Map<String, String>>{
        if(evolution.isEmpty()){
            return evo
        }
        var lv = evolution[0].evolution_details[0].min_level.toString()
        if(lv == "0"){
            lv = evolution[0].evolution_details[0].trigger.name
        }else{
            lv = "lv. ${lv}"
        }
        var regex = Regex("""\d+(?=/?$)""").find(evolution[0].species.url)?.value.toString()
        var url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${regex}.png"

        evo.add(mapOf("lv" to lv, "url" to url))

        return getEvolutionsRecursive(evolution[0].evolves_to, evo)
    }
}