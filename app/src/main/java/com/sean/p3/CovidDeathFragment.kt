package com.sean.p3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CovidDeathFragment : Fragment(R.layout.fragment_covid_death) {
    var currentDeathCovidStats: String? = ""
    var deathTitle: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_covid_death, container, false)
        currentDeathCovidStats = arguments?.getString("currentDeathStats")
        deathTitle = arguments?.getString("deathTitle")

        Log.d("death Title", deathTitle.toString())
        Log.d("current death stats", currentDeathCovidStats.toString())

        view.findViewById<TextView>(R.id.CovidDeathDataTitle).text = deathTitle
        view.findViewById<TextView>(R.id.CovidDeathTextView).text = currentDeathCovidStats

        return view
    }
}