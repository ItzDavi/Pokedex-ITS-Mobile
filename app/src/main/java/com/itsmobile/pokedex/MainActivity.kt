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
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.primary)

        val sharedPrefs = getSharedPreferences("version", Context.MODE_PRIVATE)
        binding.versionTextview.text = sharedPrefs.getString("version_num", "I")

        binding.view2.setOnClickListener {
            startActivity(Intent(this, VersionActivity::class.java))
        }

        binding.view3.setOnClickListener {
            startActivity(Intent(this, PokedexActivity::class.java))
        }

        binding.view4.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        binding.teamPokeballImageview.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPrefs = getSharedPreferences("version", Context.MODE_PRIVATE)
        binding.versionTextview.text = sharedPrefs.getString("version_num", "I")
    }
}