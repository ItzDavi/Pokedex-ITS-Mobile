package com.itsmobile.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentView, PokemonDetailFragment.newInstance("", ""))
            .commit()
    }
}