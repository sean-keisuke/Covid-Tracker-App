package com.sean.p3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class CovidPositiveFragment : Fragment(R.layout.fragment_covid_positive) {
    var currentPositiveCovidStats: String? = ""
    var positiveTitle: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_covid_positive, container, false)

        positiveTitle = arguments?.getString("positiveTitle")
        currentPositiveCovidStats = arguments?.getString("currentPositiveStats")

        Log.d("positive Title", positiveTitle.toString())
        Log.d("currentPositiveStats", currentPositiveCovidStats.toString())

        view.findViewById<TextView>(R.id.CovidPositiveDataTitle).text = positiveTitle
        view.findViewById<TextView>(R.id.CovidPositiveTextView).text = currentPositiveCovidStats
        return view
    }
}