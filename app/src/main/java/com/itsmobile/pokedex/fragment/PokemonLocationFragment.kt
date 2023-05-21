package com.itsmobile.pokedex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.adapter.LocationAdapter
import com.itsmobile.pokedex.databinding.FragmentPokemonEvolutionBinding
import com.itsmobile.pokedex.databinding.FragmentPokemonLocationBinding
import com.itsmobile.pokedex.model.location.LocationViewModel
import com.itsmobile.pokedex.model.pokemon.PokemonViewModel

class PokemonLocationFragment : Fragment() {

    private val locationModel : LocationViewModel by activityViewModels()
    private var _binding: FragmentPokemonLocationBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPokemonLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationModel.locations.observe(viewLifecycleOwner){ locations ->
            binding.locations.apply {
                adapter = LocationAdapter(locations)
                layoutManager = LinearLayoutManager(requireView().context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            PokemonLocationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}