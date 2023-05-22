package com.itsmobile.pokedex

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.itsmobile.pokedex.databinding.ActivityTeamBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class TeamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.primary)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallBack)

        val r = findViewById<ImageButton>(R.id.randomButton)
        r.setOnClickListener {
            resetVisibility()

            val fadeInAnim = AnimationUtils.loadAnimation(this@TeamActivity, R.anim.fade_in_anim)
            val viewList = listOf(binding.pokemon1, binding.pokemon2, binding.pokemon3, binding.pokemon4, binding.pokemon5, binding.pokemon6)

            for (view in viewList) {
                lifecycleScope.launch {
                    Glide.with(this@TeamActivity).load(getRandomPokemon()).centerInside().into(view)
                    view.startAnimation(fadeInAnim)
                    view.visibility = View.VISIBLE
                    delay(1000)
                }
            }
        }

        binding.backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in_anim, R.anim.slide_out_right)
        }
    }

    private fun resetVisibility() {
        val fadeOutAnim = AnimationUtils.loadAnimation(this@TeamActivity, R.anim.fade_out_anim)
        val viewList = listOf<View>(binding.pokemon1, binding.pokemon2, binding.pokemon3, binding.pokemon4, binding.pokemon5, binding.pokemon6)

        for (view in viewList) {
            view.startAnimation(fadeOutAnim)
            view.visibility = View.INVISIBLE
        }
    }

    private fun getRandomPokemon(): String {
        val rand = Random.nextInt(1, 1000)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$rand.png"
    }

    private val onBackPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
            overridePendingTransition(R.anim.fade_in_anim, R.anim.slide_out_right)
        }
    }
}