package com.itsmobile.pokedex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.itsmobile.pokedex.model.Version

class VersionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version)

        window.statusBarColor= getColor(R.color.primary)

        val sharedPref = getSharedPreferences("version", Context.MODE_PRIVATE)

        val textView = findViewById<TextView>(R.id.numVersion)
        textView.text = sharedPref.getString("version_num", "I")

        val versions = ArrayList<Version>()

        versions.add(Version("RED, BLUE", "I", "https://pokeapi.co/api/v2/pokedex/2"))
        versions.add(Version("GOLD, SILVER","II", "https://pokeapi.co/api/v2/pokedex/3"))
        versions.add(Version("RUBY, SAPPHIRE","III", "https://pokeapi.co/api/v2/pokedex/4"))
        versions.add(Version("DIAMOND, PEARL","IV", "https://pokeapi.co/api/v2/pokedex/5"))
        versions.add(Version("WHITE, BLACK","V", "https://pokeapi.co/api/v2/pokedex/8"))
        versions.add(Version("X, Y","VI", "https://pokeapi.co/api/v2/pokedex/12"))
        versions.add(Version("SUN, MOON","VII", "https://pokeapi.co/api/v2/pokedex/16"))


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(this@VersionActivity, LinearLayoutManager.VERTICAL, false)

        val adapter = VersionAdapter(versions)
        recyclerView.adapter = adapter

    }
}