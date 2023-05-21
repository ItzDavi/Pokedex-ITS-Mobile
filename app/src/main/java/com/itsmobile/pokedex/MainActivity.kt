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
        
        /*val sharedPref = this.getSharedPreferences("version", Context.MODE_PRIVATE) //creo la shared
        val myEdit = sharedPref.edit() // creo l'editor della shared
        myEdit.putString("version","https://pokeapi.co/api/v2/pokedex/3") //edito la shared
        myEdit.apply()*/

        /*val sharedPref = this.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val myEdit = sharedPref.edit()
        myEdit.putString("version","https://pokeapi.co/api/v2/pokedex/3")
        myEdit.apply()*/

        binding.view4.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        binding.teamPokeballImageview.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }
        
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