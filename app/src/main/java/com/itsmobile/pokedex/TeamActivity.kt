package com.itsmobile.pokedex

import android.R
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.util.Random


class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val r = findViewById<ImageButton>(R.id.randomButton)
        r.setOnClickListener {
            Glide.with(this).load(getRandomPokemon()).optionalFitCenter().into(binding.pokemon1)
            Glide.with(this).load(getRandomPokemon()).optionalFitCenter().into(binding.pokemon2)
            Glide.with(this).load(getRandomPokemon()).optionalFitCenter().into(binding.pokemon3)
            Glide.with(this).load(getRandomPokemon()).optionalFitCenter().into(binding.pokemon4)
            Glide.with(this).load(getRandomPokemon()).optionalFitCenter().into(binding.pokemon5)
            Glide.with(this).load(getRandomPokemon()).optionalFitCenter().into(binding.pokemon6)
        }

    }

    private fun getRandomPokemon(): String {
        val rand = Random.nextInt(1, 1000)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$rand.png"
    }
}