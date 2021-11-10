package com.sean.p3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    lateinit var currentPositiveCovidStats:String
    lateinit var currentDeathCovidStats:String
    private lateinit var selectedState: String
    lateinit var state: String
    lateinit var positiveTitle: String
    lateinit var deathTitle: String
    private lateinit var stateSpinner: Spinner
    lateinit var covidHistory: ArrayList<CovidDataResponse>
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cameraActivityButton = findViewById<Button>(R.id.cameraActivityButton)

        cameraActivityButton.setOnClickListener {
                val intent = Intent(this, CameraActivity::class.java)
                startActivity(intent)
        }

        val covidHistoryActivityButton = findViewById<Button>(R.id.covid_history_activity_button)

        covidHistoryActivityButton.setOnClickListener {
            val intent = Intent(this, CovidHistoryActivity::class.java)
            intent.putParcelableArrayListExtra("history", covidHistory)
            startActivity(intent)
        }

        // state selector

        stateSpinner = findViewById(R.id.state_spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.US_states_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = adapter
        stateSpinner.onItemSelectedListener = this
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedState = parent?.getItemAtPosition(position).toString()
        state =  StateMap().getStateID(selectedState).toString()

        val serviceDownloader = CovidDataService()
        val loadingSpinner = findViewById<ProgressBar>(R.id.loadingSpinner)


        val callback = object: Callback<List<CovidDataResponse>> {

            override fun onFailure(call: Call<List<CovidDataResponse>>, t: Throwable) {
                Log.e("mainActivity","Problems", t)
            }

            @SuppressLint("SetTextI18n")
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<List<CovidDataResponse>>, response: Response<List<CovidDataResponse>>) {

                val positiveCovidFragment = findViewById<Button>(R.id.positiveCovidFragment)
                val deathCovidFragment = findViewById<Button>(R.id.covidDeathFragment)

                val positiveFragment = CovidPositiveFragment()
                val deathFragment = CovidDeathFragment()


                response.isSuccessful.let{
                    Log.d("Response", response.toString())
                    Log.d("Response", response.body().toString())
                    val resp = response.body()

                    if (resp != null) {
                        covidHistory = resp as ArrayList<CovidDataResponse>
                    }

                    state = resp?.elementAt(0)?.state.toString()

                    val stringDate= resp?.elementAt(0)?.date

                    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH)
                    val date = LocalDate.parse(stringDate, formatter)

                    this@MainActivity.positiveTitle = getString(R.string.covid_positive_title, selectedState) + date
                    this@MainActivity.deathTitle = getString(R.string.covid_death_title, selectedState) + date
                    this@MainActivity.currentPositiveCovidStats = resp?.elementAt(0)?.positive.toString()
                    this@MainActivity.currentDeathCovidStats = resp?.elementAt(0)?.death.toString()

                    loadingSpinner.visibility = View.INVISIBLE

                    supportFragmentManager.beginTransaction().apply{
                        replace(R.id.covidStatFragment, positiveFragment)
                        bundle.putString("positiveTitle", this@MainActivity.positiveTitle)
                        bundle.putString("currentPositiveStats", this@MainActivity.currentPositiveCovidStats)
                        positiveFragment.arguments = bundle
                        commit()
                    }

                    positiveCovidFragment.setOnClickListener(){
                        supportFragmentManager.beginTransaction().apply{
                            replace(R.id.covidStatFragment, positiveFragment)
                            bundle.putString("positiveTitle", this@MainActivity.positiveTitle)
                            bundle.putString("currentPositiveStats", this@MainActivity.currentPositiveCovidStats)
                            positiveFragment.arguments = bundle
                            commit()
                        }
                    }

                    deathCovidFragment.setOnClickListener(){
                        supportFragmentManager.beginTransaction().apply{
                            replace(R.id.covidStatFragment, deathFragment)
                            bundle.putString("deathTitle", this@MainActivity.deathTitle)
                            bundle.putString("currentDeathStats", this@MainActivity.currentDeathCovidStats)
                            deathFragment.arguments = bundle
                            commit()
                        }
                    }
                }
            }
        }
        serviceDownloader.getCovidData(callback, state)
        loadingSpinner.visibility = View.VISIBLE

    }
}