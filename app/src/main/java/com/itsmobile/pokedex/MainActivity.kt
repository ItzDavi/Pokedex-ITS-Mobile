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

        binding.text.setOnClickListener{
            val intent = Intent(this,VersionActivity::class.java)
            startActivity(intent)
        }
        
       /* var intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra("url", "https://pokeapi.co/api/v2/pokemon-species/chimchar")
        startActivity(intent)

        val sharedPref = this.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val myEdit = sharedPref.edit()
        myEdit.putString("version", "platinum")
        myEdit.apply()*/
    }
}