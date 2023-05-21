package com.itsmobile.pokedex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itsmobile.pokedex.model.pokemon.PokemonEntry

class PokemonAdapter (private val pokemonEntries: ArrayList<PokemonEntry>) : RecyclerView.Adapter<PokemonAdapter.CustomViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_pokemon, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val poke = pokemonEntries[position]
        holder.prog.text = poke.entry_number.toString()
        holder.name.text = poke.pokemon_species.name
        Glide.with(holder.view.context).load(getImageFromShittyAPI(poke.pokemon_species.url)).centerCrop().into(holder.image)
       /* val number = holder.view.findViewById<TextView>(R.id.num)
        number.text = poke.getNumberString()*/
        holder.pokeView.setOnClickListener{
            Log.d("url",poke.pokemon_species.url)
            val intent  = Intent (holder.view.context,UrlActivity::class.java)
            intent.putExtra("url",poke.pokemon_species.url)
            startActivity(holder.view.context, intent, Bundle())
        }
    }

    private fun getImageFromShittyAPI(url:String) : String{
        val regex = Regex("""\d+(?=/?$)""")
        val matchResult = regex.find(url)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${matchResult?.value}.png"
    }
    override fun getItemCount() = pokemonEntries.size
    class CustomViewHolder(val view: ViewGroup): RecyclerView.ViewHolder(view){
        val prog : TextView = view.findViewById(R.id.prog)
        val name : TextView = view.findViewById(R.id.name)
        val image : ImageView = view.findViewById(R.id.image)
        val pokeView : View = view.findViewById(R.id.pokeball)
    }
}