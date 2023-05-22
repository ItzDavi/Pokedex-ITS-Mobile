package com.itsmobile.pokedex

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itsmobile.pokedex.model.Version

class VersionAdapter (private val versions: ArrayList<Version>) : RecyclerView.Adapter<VersionAdapter.CustomViewHolder>() {

    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val version = versions[position]
        val numVersion =  holder.view.findViewById<TextView>(R.id.textNumversion)
        numVersion.text = version.number
        val textVersion = holder.view.findViewById<TextView>(R.id.textversion)
        textVersion.text = version.name

        holder.itemView.setOnClickListener{
            val sharedPre = holder.itemView.context.getSharedPreferences("version", Context.MODE_PRIVATE)
            sharedPre.edit().putString("version", versions[position].url).apply()
            sharedPre.edit().putString("version_num",versions[position].number).apply()

            sendBackToActivity(holder)
        }
    }

    private fun sendBackToActivity(holder: CustomViewHolder) {
        val activity = (holder.itemView.context as Activity)
        activity.finish()
        activity.overridePendingTransition(R.anim.fade_in_anim, R.anim.slide_out_right)
    }


    override fun getItemCount() = versions.size
}