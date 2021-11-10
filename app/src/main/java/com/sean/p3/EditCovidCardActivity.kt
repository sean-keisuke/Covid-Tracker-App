package com.sean.p3

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import java.text.DateFormat
import java.util.*


class EditCovidCardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var editCovidStatusButton: Button
    private lateinit var selectedManufacturer:String
    private lateinit var selectedVaccine:String
    private lateinit var dateReceivedString:String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_covid_card)
        val editDate = findViewById<CalendarView>(R.id.editDateCalendarView)



        editCovidStatusButton = findViewById(R.id.editCovidStatusButton)

        val vaccineSpinner = findViewById<Spinner>(R.id.vaccineTypeSpinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.Vaccine_dose_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vaccineSpinner.adapter = adapter
        vaccineSpinner.onItemSelectedListener = this

        val manufacturerSpinner = findViewById<Spinner>(R.id.manufacturerTypeSpinner)
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.Vaccine_manufacturer_array,
            android.R.layout.simple_spinner_item
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        manufacturerSpinner.adapter = adapter2
        manufacturerSpinner.onItemSelectedListener = this

        // get a calendar instance
        val calendar = Calendar.getInstance()
        val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
        //set default date as now
        editDate.date = System.currentTimeMillis()
        dateReceivedString = dateFormatter.format(editDate.date)

        editDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // set the calendar date as calendar view selected date
            calendar.set(year,month,dayOfMonth)

            // format the calendar selected date
            val formattedDate = dateFormatter.format(calendar.time)

            dateReceivedString = formattedDate
        }

        editCovidStatusButton.setOnClickListener {

            val editClinicSite = findViewById<EditText>(R.id.editClinicSite)
            val clinicSite:String = editClinicSite.text.toString()

            //check if the EditText have values or not
            if (clinicSite.trim().isEmpty() ||
                clinicSite.trim().isBlank()) {
                Toast.makeText(applicationContext, "Please enter a clinic site! ", Toast.LENGTH_SHORT).show()
            }
            else{
                EventBus.getDefault().post(RadioEvent(selectedVaccine, selectedManufacturer, dateReceivedString, clinicSite))
                finish()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent!!.id == R.id.vaccineTypeSpinner) {
            selectedVaccine = parent?.getItemAtPosition(position).toString()
        } else if (parent!!.id == R.id.manufacturerTypeSpinner) {
            selectedManufacturer = parent?.getItemAtPosition(position).toString()
        }
    }
}