package com.sean.p3

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class CovidHistoryActivity : AppCompatActivity() {
    lateinit var covidHistoryFragment: CovidHistoryFragment
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_history)

        val covidHistoryList: ArrayList<CovidDataResponse>? =
            intent.getParcelableArrayListExtra<CovidDataResponse>("history")

        covidHistoryFragment = CovidHistoryFragment()


        supportFragmentManager
            .beginTransaction().apply {
                bundle.putParcelableArrayList("covidhistory", covidHistoryList)
                covidHistoryFragment.arguments = bundle
                add(R.id.fragmentContainer, covidHistoryFragment)
                commit()
            }
    }
}