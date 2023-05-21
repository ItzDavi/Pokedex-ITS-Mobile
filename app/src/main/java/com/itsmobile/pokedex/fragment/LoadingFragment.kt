package com.itsmobile.pokedex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itsmobile.pokedex.R

class LoadingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoadingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}