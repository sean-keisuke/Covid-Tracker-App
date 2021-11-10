package com.sean.p3

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class CovidCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_card)
        EventBus.getDefault().register(this)

        val currentVaccine = findViewById<TextView>(R.id.currentVaccineType)
        val currentManufacturer = findViewById<TextView>(R.id.currentManufacturer)
        val currentDate = findViewById<TextView>(R.id.currentDateReceived)
        val currentClinicSite = findViewById<TextView>(R.id.currentClinicSite)

        val covidEditButton = findViewById<Button>(R.id.editYourCovidButton)
        covidEditButton.setOnClickListener {
            val intent = Intent(this, EditCovidCardActivity::class.java)
            startActivity(intent)
        }

        val backToHomeButton = findViewById<Button>(R.id.backToHome)
        backToHomeButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val covidBox: Box<CovidCard> = ObjectBox.boxStore.boxFor()

        val query = covidBox.query {
            equal(CovidCard_.isVaxinated, true)
            order(CovidCard_.id)
        }

//        val results = covidBox.all

        val results = query.find()
        if(results.size > 0){
            currentVaccine.text = getString(R.string.vaccine_type_display, results[results.size-1].vaccine)
            currentManufacturer.text = getString(R.string.manufacturer_display, results[results.size-1].Manufacturer)
            currentDate.text = getString(R.string.date_received_display, results[results.size-1].date)
            currentClinicSite.text = getString(R.string.clinic_site_display, results[results.size-1].ClinicSite)
            Log.d("BSU", "${results.size}")
        }
        else {
            currentVaccine.text = R.string.no_covid_card_entry.toString()
            currentManufacturer.text = ""
            currentDate.text = ""
            currentClinicSite.text = ""
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event:RadioEvent){
        val eventCovidStatus = CovidCard(vaccine = event.vaccineData, Manufacturer = event.manufacturerData, date = event.dateReceived, ClinicSite = event.clinicSite, isVaxinated = true)
        val covidBox: Box<CovidCard> = ObjectBox.boxStore.boxFor()


        val currentVaccine = findViewById<TextView>(R.id.currentVaccineType)
        currentVaccine.text = getString(R.string.vaccine_type_display, event.vaccineData)

        val currentManufacturer = findViewById<TextView>(R.id.currentManufacturer)
        currentManufacturer.text = getString(R.string.manufacturer_display, event.manufacturerData)

        val currentDate = findViewById<TextView>(R.id.currentDateReceived)
        currentDate.text = getString(R.string.date_received_display, event.dateReceived)


        val currentClinicSite = findViewById<TextView>(R.id.currentClinicSite)
        currentClinicSite.text = getString(R.string.clinic_site_display, event.clinicSite)
        covidBox.put(eventCovidStatus)
    }


    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}