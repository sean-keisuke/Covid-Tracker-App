package com.sean.p3

import android.os.Build
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class CovidHistoryListAdapter(
    private val values: ArrayList<CovidDataResponse>
) : RecyclerView.Adapter<CovidHistoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_covid_history, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Log.d("item", item.toString())
        holder.covid_postive.text = "Positive Cases: ${item.positive.toString()}"
        holder.covid_death.text = "Death Cases: ${item.death.toString()}"
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH)
        val date = LocalDate.parse(item.date, formatter)
        holder.covid_date.text = "Date: ${date}"
        holder.covid_state.text = "State: ${item.state}"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val covid_date: TextView = view.findViewById(R.id.covid_date)
        val covid_postive: TextView = view.findViewById(R.id.covid_postive)
        val covid_death: TextView = view.findViewById(R.id.covid_death)
        val covid_state: TextView = view.findViewById(R.id.covid_state)


        override fun toString(): String {
            return super.toString() + " '" + covid_date.text + "'"
        }
    }
}