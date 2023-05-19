package com.itsmobile.pokedex

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.itsmobile.pokedex.databinding.ActivityTeamBinding
import kotlin.random.Random

class TeamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val r = findViewById<ImageButton>(R.id.randomButton)
        r.setOnClickListener {
            Glide.with(this).load(getRandomPokemon()).centerInside().into(binding.pokemon1)
            Glide.with(this).load(getRandomPokemon()).centerInside().into(binding.pokemon2)
            Glide.with(this).load(getRandomPokemon()).centerInside().into(binding.pokemon3)
            Glide.with(this).load(getRandomPokemon()).centerInside().into(binding.pokemon4)
            Glide.with(this).load(getRandomPokemon()).centerInside().into(binding.pokemon5)
            Glide.with(this).load(getRandomPokemon()).centerInside().into(binding.pokemon6)
        }

    }

    private fun getRandomPokemon(): String {
        val rand = Random.nextInt(1, 1000)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$rand.png"
    }
}