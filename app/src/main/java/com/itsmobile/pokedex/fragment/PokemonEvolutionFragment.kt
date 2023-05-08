package com.itsmobile.pokedex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.itsmobile.pokedex.R
import com.itsmobile.pokedex.adapter.EvolutionAdapter
import com.itsmobile.pokedex.adapter.StatAdapter
import com.itsmobile.pokedex.databinding.FragmentPokemonEvolutionBinding
import com.itsmobile.pokedex.databinding.FragmentPokemonMovesBinding
import com.itsmobile.pokedex.model.evolution.EvolutionViewModel
import com.itsmobile.pokedex.model.pokemon.PokemonViewModel

class PokemonEvolutionFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val evolutionModel : EvolutionViewModel by activityViewModels()
    private var _binding: FragmentPokemonEvolutionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPokemonEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        evolutionModel.evolution.observe(viewLifecycleOwner){ evolutions ->
            binding.evolutionRecycler.apply {
                adapter = EvolutionAdapter(evolutions)
                layoutManager = LinearLayoutManager(requireView().context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PokemonEvolutionFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}