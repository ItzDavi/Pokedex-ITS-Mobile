package com.itsmobile.pokedex

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
        /*
        var grass = LinkedHashMap<String, ArrayList<String>>();

        grass["quadruple_damage_from"] = ArrayList<String>()

        grass["double_damage_from"] = ArrayList<String>()

        grass["double_damage_from"]?.add("flying")
        grass["double_damage_from"]?.add("poison")
        grass["double_damage_from"]?.add("bug")
        grass["double_damage_from"]?.add("fire")
        grass["double_damage_from"]?.add("ice")

        grass["neutral"] = ArrayList<String>()

        grass["half_damage_from"] = ArrayList<String>()

        grass["half_damage_from"]?.add("ground")
        grass["half_damage_from"]?.add("water")
        grass["half_damage_from"]?.add("grass")
        grass["half_damage_from"]?.add("electric")

        grass["quarter_damage_from"] = ArrayList<String>()

        // type1["no_damage_from"] = ArrayList<String>()

        var poison = LinkedHashMap<String, ArrayList<String>>();

        poison["double_damage_from"] = ArrayList<String>()

        poison["double_damage_from"]?.add("ground")
        poison["double_damage_from"]?.add("psychic")

        poison["half_damage_from"] = ArrayList<String>()

        poison["half_damage_from"]?.add("fighting")
        poison["half_damage_from"]?.add("poison")
        poison["half_damage_from"]?.add("bug")
        poison["half_damage_from"]?.add("grass")
        poison["half_damage_from"]?.add("fairy")

        var doubleType = newType(grass, poison)

        */
    }

    private fun newType(grass : LinkedHashMap<String, ArrayList<String>>, poison : LinkedHashMap<String, ArrayList<String>>) : LinkedHashMap<String, ArrayList<String>>{

        var doubleType = LinkedHashMap<String, ArrayList<String>>()

        // 0 -> quarter, 1 -> double, 2 -> neutral, 3 -> half, 4 -> quarter, 5 -> no damage

        grass["double_damage_from"]?.let { type1 ->
            var typeTrash: ArrayList<String> = ArrayList<String>()
            for (type in type1){
                var isDouble = false


                poison["double_damage_from"]?.let {
                    for(secondtype in it){
                        if(type === secondtype){
                            isDouble = true
                        }
                    }
                }

                if(isDouble){
                    if(doubleType["quadruple_damage_from"].isNullOrEmpty()){
                        doubleType["quadruple_damage_from"] = ArrayList<String>()
                        doubleType["quadruple_damage_from"]?.add(type)
                    }else{
                        doubleType["quadruple_damage_from"]?.add(type)
                    }
                }else{
                    var isHalf = false

                    poison["half_damage_from"]?.let {
                        for(secondtype in it){
                            if(type === secondtype){
                                isHalf = true
                            }
                        }
                    }

                    if (!isHalf){
                        if(doubleType["double_damage_from"].isNullOrEmpty()){
                            doubleType["double_damage_from"] = ArrayList<String>()
                            doubleType["double_damage_from"]?.add(type)
                        }else{
                            doubleType["double_damage_from"]?.add(type)
                        }
                    }
                }
            }
        }

        grass["half_damage_from"]?.let{ type1 ->
            for (type in type1){
                var isDouble = false

                poison["half_damage_from"]?.let {
                    for(secondtype in it){
                        if(type === secondtype){
                            isDouble = true
                        }
                    }
                }

                if(isDouble){
                    if(doubleType["quarter_damage_from"].isNullOrEmpty()){
                        doubleType["quarter_damage_from"] = ArrayList<String>()
                        doubleType["quarter_damage_from"]?.add(type)
                    }else{
                        doubleType["quarter_damage_from"]?.add(type)
                    }
                }else{
                    var isHalf = false

                    poison["half_damage_from"]?.let {
                        for(secondtype in it){
                            if(type === secondtype){
                                isHalf = true
                            }
                        }
                    }

                    if (!isHalf){
                        if(doubleType["half_damage_from"].isNullOrEmpty()){
                            doubleType["half_damage_from"] = ArrayList<String>()
                            doubleType["half_damage_from"]?.add(type)
                        }else{
                            doubleType["half_damage_from"]?.add(type)
                        }
                    }
                }
            }
        }

        return doubleType
    }
}