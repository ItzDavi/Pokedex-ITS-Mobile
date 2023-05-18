package com.itsmobile.pokedex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itsmobile.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        val sharedPref = this.getSharedPreferences("version", Context.MODE_PRIVATE) //creo la shared
        val myEdit = sharedPref.edit() // creo l'editor della shared
        myEdit.putString("version","https://pokeapi.co/api/v2/pokedex/3") //edito la shared
        myEdit.apply()
        binding.kek.setOnClickListener {
            val intent  = Intent (this,PokedexActivity::class.java)  //fa comunicare questa Activity(this) e l'altra act (newactivity)
            startActivity(intent)//avvia l'activity indicata nell'intent
            //finish()
        }

    }
}