package com.itsmobile.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itsmobile.pokedex.adapter.AbilityAdapter
import com.itsmobile.pokedex.adapter.TypeAdapter
import com.itsmobile.pokedex.adapter.StatAdapter
import com.itsmobile.pokedex.model.Ability
import com.itsmobile.pokedex.model.Stat
import com.itsmobile.pokedex.model.Type

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PokemonDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonImage = view.findViewById<ImageView>(R.id.pokemonImageView)

        Glide.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png").into(pokemonImage)

        val stats: ArrayList<Stat> = ArrayList()

        stats.add(Stat("hp", 80.0))
        stats.add(Stat("atk", 82.0))

        var tot: Double = 0.0

        for(stat in stats){
            tot += stat.value
        }

        stats.add(Stat("tot", tot))

        val statRecycler = view.findViewById<RecyclerView>(R.id.statsRecycler)

        val statAdapter = StatAdapter(stats)

        statRecycler.apply {
            adapter = statAdapter
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }

        val abilities: ArrayList<Ability> = ArrayList()

        abilities.add(Ability("overgrow", "When this Pokémon has 1/3 or less of its HP remaining, its grass-type moves inflict 1.5× as much regular damage."))
        abilities.add(Ability("Chlorophyll", "This Pokémon's Speed is doubled during strong sunlight. This bonus does not count as a stat modifier."))

        val abilityRecycler = view.findViewById<RecyclerView>(R.id.abilityRecycler)
        val abilityAdapter = AbilityAdapter(abilities)

        val myLinearLayoutManager = object : LinearLayoutManager(view.context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        abilityRecycler.adapter = abilityAdapter
        abilityRecycler.layoutManager = myLinearLayoutManager

        val types : ArrayList<Type> = ArrayList()

        types.add(Type("grass"))
        types.add(Type("poison"))

        val typeRecycler = view.findViewById<RecyclerView>(R.id.typeRecycler)
        val typeAdapter = TypeAdapter(types)

        typeRecycler.apply {
            adapter = typeAdapter
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}