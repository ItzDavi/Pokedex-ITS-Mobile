package com.itsmobile.pokedex

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
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

        val shakeLeftAnim = AnimationUtils.loadAnimation(this@TeamActivity, R.anim.shake_left)
        val shakeRightAnim = AnimationUtils.loadAnimation(this@TeamActivity, R.anim.shake_right)
        val shakeToCenterLeftAnim = AnimationUtils.loadAnimation(this@TeamActivity, R.anim.shake_to_center_left)
        val shakeToCenterRightAnim = AnimationUtils.loadAnimation(this@TeamActivity, R.anim.shake_to_center_right)

        val pokeballList = listOf(binding.pokeball1, binding.pokeball2, binding.pokeball3, binding.pokeball4, binding.pokeball5, binding.pokeball6)
        val viewList = listOf(binding.pokemon1, binding.pokemon2, binding.pokemon3, binding.pokemon4, binding.pokemon5, binding.pokemon6)

        val r = findViewById<ImageButton>(R.id.randomButton)
        r.setOnClickListener {
            var animCounter = 0

            lifecycleScope.launch {
                while (animCounter != viewList.size) {
                    for (i in 1..2) {
                        pokeballList[animCounter].startAnimation(shakeRightAnim)
                        delay(shakeRightAnim.duration)
                        // Animate pokeball back to center
                        pokeballList[animCounter].startAnimation(shakeToCenterRightAnim)
                        delay(shakeToCenterRightAnim.duration)

                        // Animate pokeball to the left
                        pokeballList[animCounter].startAnimation(shakeLeftAnim)
                        delay(shakeRightAnim.duration)
                        // Animate pokeball back to center
                        pokeballList[animCounter].startAnimation(shakeToCenterLeftAnim)
                        delay(shakeToCenterRightAnim.duration)
                    }

                    Glide.with(this@TeamActivity).load(getRandomPokemon()).centerInside().into(viewList[animCounter])
                    viewList[animCounter].visibility = View.VISIBLE

                    delay(500)

                    animCounter++
                }
            }
        }

        binding.backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in_anim, R.anim.slide_out_right)
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