package com.itsmobile.pokedex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.itsmobile.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this, PokemonDetailActivity::class.java)
        startActivity(intent)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString("version", "red-blue")
            apply()
        }
    }
}