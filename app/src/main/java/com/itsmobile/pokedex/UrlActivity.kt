package com.itsmobile.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itsmobile.pokedex.databinding.ActivityUrlBinding

class UrlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)
        binding = ActivityUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent?.getStringExtra("url")
        binding.provaccia.text = url.toString()
    }
}