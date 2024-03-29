package com.itsmobile.pokedex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
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

    private lateinit var binding: ActivityPokemonDetailBinding
    private val viewModel : PokemonViewModel by viewModels()
    private val locationsViewModel : LocationViewModel by viewModels()
    private val evolutionViewModel : EvolutionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.primary)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentView, LoadingFragment.newInstance())
            .commit()

        getPokemonSpecies(intent?.getStringExtra("url") ?: "not found")

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.info.setOnClickListener {
            deselectAll()
            it.alpha = 1.0F
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonDetailFragment.newInstance())
                .commit()
        }

        binding.evolution.setOnClickListener {
            deselectAll()
            it.alpha = 1.0F
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonEvolutionFragment.newInstance())
                .commit()
        }

        binding.location.setOnClickListener {
            deselectAll()
            it.alpha = 1.0F
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonLocationFragment.newInstance())
                .commit()
        }

        binding.moves.setOnClickListener {
            deselectAll()
            it.alpha = 1.0F
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentView, PokemonMovesFragment.newInstance())
                .commit()
        }
    }
    private fun deselectAll(){
        binding.moves.alpha = 0.7F
        binding.info.alpha = 0.7F
        binding.evolution.alpha = 0.7F
        binding.location.alpha = 0.7F
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
                val sharedPref = this.getSharedPreferences("version", Context.MODE_PRIVATE)
                var version = sharedPref.getString("version", "")

                when(version){
                    "https://pokeapi.co/api/v2/pokedex/2"-> version = "red-blue"
                    "https://pokeapi.co/api/v2/pokedex/3" -> version = "gold-silver"
                    "https://pokeapi.co/api/v2/pokedex/4" -> version = "ruby-sapphire"
                    "https://pokeapi.co/api/v2/pokedex/5" -> version = "platinum"
                    "https://pokeapi.co/api/v2/pokedex/8" -> version = "black-white"
                    "https://pokeapi.co/api/v2/pokedex/12" -> version = "x-y"
                    "https://pokeapi.co/api/v2/pokedex/16" -> version = "sun-moon"
                }

                pokemon.movesFilteredByLevel = pokemon.getFilteredMoves("level-up", version ?: "red-blue")
                pokemon.movesFilteredByMachine = pokemon.getFilteredMoves("machine", version ?: "red-blue")
                pokemon.movesFilteredByEggs = pokemon.getFilteredMoves("egg", version ?: "red-blue")

                pokemon.movesFilteredByMachine.forEach {
                    it.move.setMachine(this, "machine", version ?: "")
                }

                pokemon.movesFilteredByLevel.forEach {
                    it.move.setMachine(this, "level-up", version ?: "")
                }

                pokemon.movesFilteredByEggs.forEach {
                    it.move.setMachine(this, "Egg", version ?: "")
                }

                viewModel.pokemon.value = pokemon
                getLocation(response.getString("location_area_encounters"))
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

        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                val sharedPref = this.getSharedPreferences("version", Context.MODE_PRIVATE)
                var version = sharedPref.getString("version", "")

                when(version){
                    "https://pokeapi.co/api/v2/pokedex/2" -> version = "red-blue"
                    "https://pokeapi.co/api/v2/pokedex/3" -> version = "gold-silver"
                    "https://pokeapi.co/api/v2/pokedex/4" -> version = "ruby-sapphire"
                    "https://pokeapi.co/api/v2/pokedex/5" -> version = "platinum"
                    "https://pokeapi.co/api/v2/pokedex/8" -> version = "black-white"
                    "https://pokeapi.co/api/v2/pokedex/12" -> version = "x-y"
                    "https://pokeapi.co/api/v2/pokedex/16" -> version = "sun-moon"
                }

                val locations = version?.let { ver ->
                    Gson().fromJson(response.toString(), Locations::class.java).getLocationFilteredByVersion(ver)
                }

                locationsViewModel.locations.value = locations

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
                val regex = Regex("""\d+(?=/?$)""").find(evolutions.chain.species.url)?.value.toString()
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
        if (lv == "0"){
            lv = evolution[0].evolution_details[0].trigger.name
        }else{
            lv = "lv. ${lv}"
        }
        val regex = Regex("""\d+(?=/?$)""").find(evolution[0].species.url)?.value.toString()
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${regex}.png"

        evo.add(mapOf("lv" to lv, "url" to url))

        return getEvolutionsRecursive(evolution[0].evolves_to, evo)
    }
}