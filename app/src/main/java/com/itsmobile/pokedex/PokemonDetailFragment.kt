package com.itsmobile.pokedex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.itsmobile.pokedex.adapter.AbilityAdapter
import com.itsmobile.pokedex.adapter.TypeAdapter
import com.itsmobile.pokedex.adapter.StatAdapter
import com.itsmobile.pokedex.databinding.FragmentPokemonDetailBinding
import com.itsmobile.pokedex.model.Ability
import com.itsmobile.pokedex.model.Stat
import com.itsmobile.pokedex.model.Type
import com.itsmobile.pokedex.model.pokemon.Pokemon
import com.itsmobile.pokedex.model.pokemon.PokemonViewModel
import com.itsmobile.pokedex.model.pokemon.StatInside


class PokemonDetailFragment : Fragment() {


    private var _binding: FragmentPokemonDetailBinding? = null

    private val binding get() = _binding!!

    private val pokemonModel : PokemonViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // url?.let { getPokemonSpecies(it) }

        pokemonModel.pokemon.observe(viewLifecycleOwner){ pokemon ->
            Glide.with(this).load(pokemon.sprites.front_default).into(binding.pokemonImageView)

            binding.pokemonName.text = pokemon.name.uppercase()
            binding.weight.text = pokemon.weight.toString()
            binding.height.text = pokemon.height.toString()
            binding.baseExperience.text = pokemon.base_experience.toString()

            var tot: Double = 0.0

            for(stat in pokemon.stats){
                tot += stat.base_stat
            }

            pokemon.stats.add(Stat(tot, StatInside("tot")))

            val statRecycler = binding.statsRecycler

            val statAdapter = StatAdapter(pokemon.stats)

            statRecycler.apply {
                adapter = statAdapter
                layoutManager = LinearLayoutManager(requireView().context, LinearLayoutManager.VERTICAL, false)
            }


            val abilityRecycler = binding.abilityRecycler
            val abilityAdapter = AbilityAdapter(pokemon.abilities)

            val myLinearLayoutManager = object : LinearLayoutManager(requireView().context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            abilityRecycler.adapter = abilityAdapter
            abilityRecycler.layoutManager = myLinearLayoutManager

            val typeRecycler = requireView().findViewById<RecyclerView>(R.id.typeRecycler)
            val typeAdapter = TypeAdapter(pokemon.types)

            typeRecycler.apply {
                adapter = typeAdapter
                layoutManager = LinearLayoutManager(requireView().context, LinearLayoutManager.HORIZONTAL, false)
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PokemonDetailFragment().apply {

            }
    }
}