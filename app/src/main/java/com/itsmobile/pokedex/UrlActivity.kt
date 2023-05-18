package com.itsmobile.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.itsmobile.pokedex.databinding.ActivityMainBinding
import com.itsmobile.pokedex.databinding.ActivityUrlBinding

class UrlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)
        binding = ActivityUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent?.getStringExtra("url")
        Log.d("kek", url.toString())
        binding.provaccia.text = url.toString()
    }
}